package top.tocchen.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.tocchen.entity.DataDictEntity;
import top.tocchen.entity.ExportDataDictEntity;
import top.tocchen.mapper.DataDictMapper;
import top.tocchen.service.DataDictService;
import top.tocchen.utils.DBUtil;
import top.tocchen.utils.exceptions.DataFormatException;
import top.tocchen.utils.exceptions.ExecuteException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tocchen
 * @date 2023/2/13 18:20
 * @since jdk 1.8
 **/
@Service
public class DataDictServiceService implements DataDictService {

    @Autowired
    private DataDictMapper mapper;

    public List<DataDictEntity> queryByParentId(int parentId) {
        List<DataDictEntity> dataDictList = mapper.queryByParentId(parentId);
        dataDictList.forEach(
                (tmp) -> {
                    boolean child = mapper.queryChildCount(tmp.getId()) > 0;
                    tmp.setChild(child);
                }
        );
        return dataDictList;
    }

    @Override
    public void exportData(HttpServletResponse response) {
        try{
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String exportFileName = URLEncoder.encode("数据字典","UTF-8");
            response.setHeader("Content-Disposition","attachment;fileName="+exportFileName+".xlsx");
            // Query Data
            List<DataDictEntity> dataDictEntities = mapper.queryAllData();
            List<ExportDataDictEntity> result = new ArrayList<>();
            dataDictEntities.forEach(
                    tmp -> {
                        result.add(new ExportDataDictEntity(tmp));
                    }
            );
            EasyExcel.write(response.getOutputStream(),ExportDataDictEntity.class)
                    .sheet("dataDict")
                    .doWrite(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(rollbackFor = ExecuteException.class)
    public void importData(MultipartFile multipartFile)  {
        try {
            EasyExcel.read(multipartFile.getInputStream(),
                    ExportDataDictEntity.class,
                    new AnalysisEventListener<ExportDataDictEntity>(){
                        @Override
                        public void invoke(ExportDataDictEntity o, AnalysisContext analysisContext) {
                            mapper.saveByExportEntity(o);
                        }
                        @Override
                        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                        }
                    }
                    ).sheet().doRead();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new DataFormatException();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ExecuteException();
        }

    }

}

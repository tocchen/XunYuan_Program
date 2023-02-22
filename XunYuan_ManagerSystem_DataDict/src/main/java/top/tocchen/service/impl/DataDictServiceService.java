package top.tocchen.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
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
import top.tocchen.utils.redis.RedisCacheKeyGenerator;
import top.tocchen.utils.redis.RedisDBName;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author tocchen
 * @date 2023/2/13 18:20
 * @since jdk 1.8
 **/
@Service
public class DataDictServiceService implements DataDictService {

    @Autowired
    private DataDictMapper mapper;

    @Autowired
    private RedisTemplate redisTemplate;


    public List<DataDictEntity> queryByParentId(int parentId) {

        String key = RedisCacheKeyGenerator.generatorAllKey("queryByParentId",RedisDBName.REDIS_DATADICT_NAME, String.valueOf(parentId));
        BoundListOperations boundListOperations = redisTemplate.boundListOps(key);
        List range = boundListOperations.range(0, -1);
        List<DataDictEntity> dataDictList = null;
        if (range != null && range.size() != 0){
            dataDictList = new ArrayList<>(range.size());
            for(Object o : range){
                String tempKey = RedisCacheKeyGenerator.generatorByIdKey(RedisDBName.REDIS_DATADICT_NAME, String.valueOf(o));
                BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(tempKey);
                Object value = boundValueOperations.get();
                if (value instanceof DataDictEntity){
                    dataDictList.add((DataDictEntity)value);
                }
            }
        }else{
            dataDictList = mapper.queryByParentId(parentId);
            dataDictList.forEach(
                    (tmp) -> {
                        boolean child = mapper.queryChildCount(tmp.getId()) > 0;
                        tmp.setChild(child);
                        String tempKey = RedisCacheKeyGenerator.generatorByIdKey(RedisDBName.REDIS_DATADICT_NAME,String.valueOf(tmp.getId()));
                        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(tempKey);
                        boundValueOperations.set(tmp);
                        boundValueOperations.expire(1,TimeUnit.DAYS);
                        boundListOperations.rightPush(tmp.getId());
                        boundListOperations.expire(1,TimeUnit.DAYS);
                    }
            );
        }
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
                            String listKey = RedisCacheKeyGenerator.generatorAllKey("queryByParentId",RedisDBName.REDIS_DATADICT_NAME, String.valueOf(o.getParentId()));
                            BoundListOperations boundListOperations = redisTemplate.boundListOps(listKey);
                            boundListOperations.rightPush(o.getId());
                            boundListOperations.expire(1,TimeUnit.DAYS);
                            String valueKey = RedisCacheKeyGenerator.generatorByIdKey(RedisDBName.REDIS_DATADICT_NAME, String.valueOf(o.getId()));
                            BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(valueKey);
                            boundValueOperations.set(o);
                            boundValueOperations.expire(1,TimeUnit.DAYS);
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

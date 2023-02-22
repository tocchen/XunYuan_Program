package top.tocchen.service;

import org.springframework.web.multipart.MultipartFile;
import top.tocchen.entity.DataDictEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author tocchen
 * @date 2023/2/13 18:20
 * @since jdk 1.8
 **/
public interface DataDictService {

    /**
     * 通过parentId查询值
     * @param parentId parentId
     * @return 查询结果集
     */
    List<DataDictEntity> queryByParentId (int parentId);


    /**
     * 将数据导出至Excel当中
     * @param response response
     */
    void exportData(HttpServletResponse response);

    /**
     * 导入Excel数据到MySQL当中
     * @param multipartFile multipartFile
     */
    void importData(MultipartFile multipartFile);
}

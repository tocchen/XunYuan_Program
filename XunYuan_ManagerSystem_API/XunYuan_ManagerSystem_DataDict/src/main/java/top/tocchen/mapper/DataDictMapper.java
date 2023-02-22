package top.tocchen.mapper;

import org.springframework.stereotype.Component;
import top.tocchen.entity.DataDictEntity;
import top.tocchen.entity.ExportDataDictEntity;

import java.util.List;

/**
 * @author tocchen
 * @date 2023/2/13 18:23
 * @since jdk 1.8
 **/
@Component
public interface DataDictMapper {

    /**
     * 通过parentId查询值
     * @param parentId parentId
     * @return 查询结果集
     */
    List<DataDictEntity> queryByParentId (int parentId);

    /**
     * 查询所有数据
     * @return
     */
    List<DataDictEntity> queryAllData();

    /**
     * 通过 parentId 查询其下是否有子内容
     * @param parentId parentId
     * @return 查询结果
     */
    int queryChildCount(int parentId);

    /**
     * 保存ExportDataDictEntity对象到DB当中
     * @param o ExportDataDictEntity
     */
    void saveByExportEntity(ExportDataDictEntity o);
}

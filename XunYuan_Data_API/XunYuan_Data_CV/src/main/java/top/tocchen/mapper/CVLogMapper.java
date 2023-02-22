package top.tocchen.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import top.tocchen.entity.CVLogEntity;

import java.util.List;

/**
 * @author tocchen
 * @date 2023/2/20 20:24
 * @since jdk 1.8
 **/
@Component
public interface CVLogMapper {
    /**
     * 查询所有记录 -- -- 分页查询
     * @return list
     */
    List<CVLogEntity> queryAll(@Param("start") int start,@Param("pageSize") int pageSize);

    int queryAllCount();

    /**
     * 通过公司id查询记录 -- -- 分页查询
     * @param companyId 公司id
     * @return list
     */
    List<CVLogEntity> queryAllByCompanyId(@Param("companyId") String companyId,@Param("start") int start,@Param("pageSize") int pageSize);


    int queryAllByCompanyIdCount(@Param("companyId") String companyId);

    /**
     * 通过用户id查询记录 -- 分页查询
     * @param userId 用户id
     * @return list
     */
    List<CVLogEntity> queryAllByUserId(@Param("userId") String userId,@Param("start") int start,@Param("pageSize") int pageSize);


    int queryAllByUserIdCount(@Param("userId") String userId);

    /**
     * 添加记录
     * @param entity entity
     */
    void insertCVLog(CVLogEntity entity);

    /**
     * 删除记录
     * @param id id值
     * @return 返回结果 1代表成功 0代表失败
     */
    int removeCVLog(String id);

}

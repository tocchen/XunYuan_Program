package top.tocchen.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import top.tocchen.entity.CompanyEntity;

import javax.annotation.security.PermitAll;
import java.util.List;

/**
 * @author tocchen
 * @date 2023/2/11 12:34
 * @since jdk 1.8
 **/
@Component
public interface CompanyMapper {

    /**
     * 分页查询 所有公司
     * @param start 当前页
     * @param pageSize 每页个数
     * @return 查询结果集合
     */
    List<CompanyEntity> queryPageAllCompany(@Param("start") int start,@Param("pageSize") int pageSize);

    /**
     * 通过id字段 查询公司
     * @param id id
     * @return 公司对象
     */
    CompanyEntity queryCompanyById(@Param("id") String id);

    /**
     * 通过 id字段 删除公司
     * @param id id
     * @return 返回结果 1代表删除成功 其他代表删除失败
     */
    int removeCompanyById(@Param("id") String id);

    /**
     * 通过 对象中的id字段 更新公司信息
     * @param companyEntity 公司对象
     * @return 返回结果 1代表删除成功 其他代表删除失败
     */
    int updateCompanyById(CompanyEntity companyEntity);

    /**
     * 通过id字段 更改公司状态
     * @param id id
     * @return 返回结果 1代表删除成功 其他代表删除失败
     */
    int updateCompanyStatusById(@Param("id") String id);

    /**
     * 通过id字段更改公司的私发密钥
     * @param id id
     * @param signKey 密钥
     * @return 返回结果 1代表删除成功 其他代表删除失败
     */
    int updateCompanySignKeyById(@Param("id") String id,@Param("signKey") String signKey);

    /**
     * 添加公司
     * @param companyEntity 公司对象
     */
    void saveCompany(CompanyEntity companyEntity);

    /**
     * 查询公司数量
     * @return 查询结果
     */
    int queryAllCompanyCount();

    /**
     * 查询公司名称是否存在
     * @param companyName 公司名称
     * @return 返回结果 1代表删除成功 其他代表删除失败
     */
    int queryCompanyNameExist(@Param("companyName") String companyName);

    /**
     * 模糊分页查询 查询公司名称、公司编号、公司地址及、负责人、负责人电话
     * @param parameter 条件
     * @param start 开始
     * @param pageSize 每页大小
     * @return 查询结果集
     */
    List<CompanyEntity> queryCompanyLikeNameCodeAddress(@Param("parameter") String parameter,
                                                        @Param("start") int start,
                                                        @Param("pageSize") int pageSize);

    /**
     * 分页总数
     * @param parameter 条件
     * @return 总数
     */
    int queryPageCount(@Param("parameter") String parameter);

    /**
     * 查询星级公司
     * @return  List CompanyEntity
     */
    List<CompanyEntity> queryStarCompany();
}

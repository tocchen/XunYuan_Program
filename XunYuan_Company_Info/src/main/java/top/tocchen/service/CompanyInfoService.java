package top.tocchen.service;

import top.tocchen.entity.CompanyInfoEntity;
import top.tocchen.vo.CompanyInfoVo;

import java.util.List;

/**
 * @author tocchen
 * @date 2023/2/19 16:49
 * @since jdk 1.8
 **/
public interface CompanyInfoService {

    void saveCompanyInfo(CompanyInfoVo companyInfoVo);

    Long deletedCompanyInfoById(String id);

    CompanyInfoEntity queryCompanyById(String id);

    Long updateCompanyInfoById(CompanyInfoEntity entity);

    List<CompanyInfoEntity> queryCompanyByName(String name);
}

package top.tocchen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tocchen.entity.CompanyEntity;
import top.tocchen.mapper.CompanyMapper;
import top.tocchen.service.CompanyService;
import top.tocchen.utils.DBUtil;
import top.tocchen.utils.exceptions.DataExistsException;
import top.tocchen.utils.exceptions.QueryException;
import top.tocchen.utils.http.Response;

import java.util.List;

/**
 * @author tocchen
 * @date 2023/2/11 14:21
 * @since jdk 1.8
 **/
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;


    public List<CompanyEntity> queryPageAllCompany(int current, int pageSize) {
        return companyMapper.queryPageAllCompany((current-1)*pageSize,pageSize);
    }

    public CompanyEntity queryCompanyById(String id) {
        CompanyEntity result = companyMapper.queryCompanyById(id);
        DBUtil.isEmpty2QueryException(result);
        return result;
    }

    public int removeCompanyById(String id) {
        return companyMapper.removeCompanyById(id);
    }

    public int updateCompanyById(CompanyEntity companyEntity) {
        return companyMapper.updateCompanyById(companyEntity);
    }

    public int updateCompanyStatusById(String id) {
        return companyMapper.updateCompanyStatusById(id);
    }

    public int updateCompanySignKeyById(String id, String signKey) {
        return companyMapper.updateCompanySignKeyById(id, signKey);
    }

    public void saveCompany(CompanyEntity companyEntity) {
        int i = companyMapper.queryCompanyNameExist(companyEntity.getCompanyName());
        if (i != 0){
            throw new DataExistsException();
        }
        companyMapper.saveCompany(companyEntity);
    }

    public int queryAllCompanyCount() {
        return companyMapper.queryAllCompanyCount();
    }

    public List<CompanyEntity> queryCompanyLikeNameCodeAddress(String parameter, int current, int pageSize) {
        return companyMapper.queryCompanyLikeNameCodeAddress(parameter,(current-1)*pageSize,pageSize);
    }

    public int queryPageCount(String parameter) {
        return companyMapper.queryPageCount(parameter);
    }

    public int queryCompanyNameExist(String companyName) {
        return companyMapper.queryCompanyNameExist(companyName);
    }
}

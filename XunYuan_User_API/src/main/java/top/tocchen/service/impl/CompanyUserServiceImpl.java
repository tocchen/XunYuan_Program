package top.tocchen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tocchen.entity.CompanyUser;
import top.tocchen.mapper.CompanyUserMapper;
import top.tocchen.service.CompanyUserService;
import top.tocchen.utils.MD5Util;
import top.tocchen.utils.UUIDUtil;
import top.tocchen.utils.exceptions.UserExistNotRegisterException;
import top.tocchen.vo.CompanyUserVo;

/**
 * @author tocchen
 * @date 2023/2/23 21:21
 * @since jdk 1.8
 **/
@Service
public class CompanyUserServiceImpl implements CompanyUserService {

    @Autowired private CompanyUserMapper userMapper;

    public void insertCompanyUser(CompanyUserVo companyUser) {
        if (userMapper.existCompanyUserByCompanyName(companyUser.getCompanyName()) != 0){
            throw new UserExistNotRegisterException();
        }
        CompanyUser user = new CompanyUser(
                UUIDUtil.generateUUID(),
                companyUser.getCompanyName(),
                companyUser.getSign(),
                companyUser.getCompanyUserPhone(),
                companyUser.getCompanyUserName(),
                MD5Util.generateMd5(companyUser.getPassword())
        );
        userMapper.insertCompanyUser(user);
    }

    public CompanyUser queryCompanyUserByPhone(CompanyUserVo companyUser) {
        CompanyUser result =  userMapper.queryCompanyUserByPhone(companyUser.getCompanyUserPhone());
        return result;
    }
}

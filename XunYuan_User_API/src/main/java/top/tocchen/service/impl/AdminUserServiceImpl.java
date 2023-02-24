package top.tocchen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tocchen.entity.AdminUser;
import top.tocchen.mapper.AdminUserMapper;
import top.tocchen.service.AdminUserSerivce;
import top.tocchen.utils.MD5Util;
import top.tocchen.utils.UUIDUtil;
import top.tocchen.utils.exceptions.DataFormatException;
import top.tocchen.utils.exceptions.UserExistNotRegisterException;
import top.tocchen.vo.AdminUserVo;

/**
 * @author tocchen
 * @date 2023/2/23 20:22
 * @since jdk 1.8
 **/
@Service
public class AdminUserServiceImpl implements AdminUserSerivce {

    @Autowired private AdminUserMapper adminUserMapper;

    public void insertAdminUser(AdminUserVo adminUser) {
        if (adminUserMapper.existAdminUserByJobNumber(adminUser.getJobNumber()) != 0){
            throw new UserExistNotRegisterException();
        }
        AdminUser user = new AdminUser(
                UUIDUtil.generateUUID(),
                adminUser.getEmail(),
                adminUser.getPhone(),
                adminUser.getJobNumber(),
                MD5Util.generateMd5(adminUser.getPassword())
        );
        adminUserMapper.insertAdminUser(user);
    }

    public AdminUser queryAdminByAccountAndJobNumber(AdminUserVo adminUserVo) {
        AdminUser result = null;
        if (adminUserVo.getJobNumber() == null || "".equals(adminUserVo.getJobNumber())){
            throw new DataFormatException();
        }
        if (adminUserVo.getPhone() != null && !("".equals(adminUserVo.getPhone())) ){
            result = adminUserMapper.
                    queryAdminUserByAccountAndJobNumber(adminUserVo.getPhone(),adminUserVo.getJobNumber());
        }else if(adminUserVo.getEmail() != null && !("".equals(adminUserVo.getEmail()))){
            result = adminUserMapper.
                    queryAdminUserByAccountAndJobNumber(adminUserVo.getEmail(),adminUserVo.getJobNumber());
        }
        return result;
    }
}

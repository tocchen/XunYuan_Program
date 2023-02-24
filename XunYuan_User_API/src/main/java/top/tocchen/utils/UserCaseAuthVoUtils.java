package top.tocchen.utils;

import top.tocchen.entity.AdminUser;
import top.tocchen.entity.CompanyUser;
import top.tocchen.entity.NormalUser;
import top.tocchen.enums.UserRoleEnum;
import top.tocchen.utils.exceptions.DataFormatException;
import top.tocchen.vo.UserAuthVO;

/**
 * @author tocchen
 * @date 2023/2/24 11:32
 * @since jdk 1.8
 **/
public class UserCaseAuthVoUtils {

    public static <E> UserAuthVO use2Auth(E user){
        UserAuthVO result = null;
        if (user instanceof AdminUser){
            result = new UserAuthVO(
                    ((AdminUser) user).getId(),
                    ((AdminUser) user).getUserName(),
                    ((AdminUser) user).getSalt(),
                    ((AdminUser) user).getPassword(),
                    UserRoleEnum.ADMIN_USER,
                    ((AdminUser) user).getJobNumber(),
                    null
            );
        }else if(user instanceof CompanyUser){
            result = new UserAuthVO(
                    ((CompanyUser) user).getId(),
                    ((CompanyUser) user).getCompanyUserName(),
                    ((CompanyUser) user).getCompanyUserSalt(),
                    ((CompanyUser) user).getPassword(),
                    UserRoleEnum.COMPANY_USER,
                    null,
                    ((CompanyUser) user).getCompanyName()
            );
        }else if(user instanceof NormalUser){
            result = new UserAuthVO(
                    ((NormalUser) user).getId(),
                    ((NormalUser) user).getUserName(),
                    ((NormalUser) user).getUserSalt(),
                    ((NormalUser) user).getUserPassword(),
                    UserRoleEnum.NORMAL_USER,
                    null,
                    ((NormalUser) user).getUserPhone()+","+((NormalUser) user).getUserPhone()
            );
        }else {
            throw new DataFormatException();
        }
        return result;
    }

}

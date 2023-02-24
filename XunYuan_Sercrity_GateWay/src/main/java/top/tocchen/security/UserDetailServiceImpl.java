package top.tocchen.security;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import top.tocchen.feign.AdminUserFeign;
import top.tocchen.feign.NormalUserFeign;
import top.tocchen.utils.http.Response;
import top.tocchen.vo.UserAuthVO;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * @author tocchen
 * @date 2023/2/24 14:36
 * @since jdk 1.8
 **/
@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    AdminUserFeign adminUserFeign;
    @Autowired
    NormalUserFeign normalUserFeign;

    private static boolean isSimpleEmail(String str){
        String regex = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        if (str == null || str.length() < 1 || str.length() > 256) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(str).matches();
    }


    // TODO 通过用户名获取账号信息
    // TODO 自定义UserDetails
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CustomUserDetailsImpl(new UserAuthVO(null,username,null,
                "123456",null,null,null));
    }

    // TODO 根据业务要求添加三种获取账户信息的方法

    /**
     * 获取管理员信息
     * @param account
     * @param jobNumber
     * @return
     */
    public CustomUserDetailsImpl loadAdminUserByAccountAndJobNumber(String account,String jobNumber){
        HashMap<String, Object> map = new HashMap<String, Object>(1);
        if (isSimpleEmail(account)){
            map.put("email",account);
        }else{
            map.put("phone",account);
        }
        map.put("jobNumber",jobNumber);
        JSONObject json = new JSONObject(map);
        Response<?> response = adminUserFeign.queryAdminUser(json);
        UserAuthVO data = (UserAuthVO) response.getData();
        return new CustomUserDetailsImpl(data);
    }

    public CustomUserDetailsImpl loadNormalUserByPhoneOrAccount(String params){
        return null;
    }
}

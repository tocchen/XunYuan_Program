package top.tocchen.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tocchen.feign.AdminUserFeign;
import top.tocchen.security.CustomUserDetailsImpl;
import top.tocchen.utils.JwtTokenUtil;
import top.tocchen.utils.MD5Util;
import top.tocchen.utils.http.HttpStatusEnum;
import top.tocchen.utils.http.Response;
import top.tocchen.vo.UserAuthVO;

/**
 * @author tocchen
 * @date 2023/2/24 17:14
 * @since jdk 1.8
 **/
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    AdminUserFeign adminUserFeign;

    @PostMapping("/admin")
    public Response<?> loginAdmin(@RequestBody JSONObject jsonObject){
        Response<?> response = adminUserFeign.queryAdminUser(jsonObject);
        if (response.getCode() == 200){
            UserAuthVO data = (UserAuthVO) response.getData();
            boolean isSuccess = MD5Util.equalsMd5(data.getPassword(), jsonObject.getString("password"), data.getSalt());
            if (isSuccess){
                String token = jwtTokenUtil.generateToken(new CustomUserDetailsImpl(data));
                Response<?> result = Response.success();
                result.setToken(token);
                return result;
            }
        }
        return Response.fail(HttpStatusEnum.USER_AUTH_FAIL);
    }
}

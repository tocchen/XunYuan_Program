package top.tocchen.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tocchen.feign.AdminUserFeign;
import top.tocchen.utils.http.Response;

import java.util.HashMap;

/**
 * @author tocchen
 * @date 2023/2/24 14:34
 * @since jdk 1.8
 **/
@RestController
public class HelloController {

    @Autowired
    AdminUserFeign adminUserFeign;

    @GetMapping("/hello")
    public String hello(){
        return "hello World";
    }

    /** 通过账号名称和工号查询用户 */
    @GetMapping("/user")
    public Object user(){
        HashMap<String, Object> map = new HashMap<String, Object>(1);
        map.put("email","tocchen@163.com");
        map.put("jobNumber","197701040003");
        JSONObject jsonObject = new JSONObject(map);
        Response<?> response = adminUserFeign.queryAdminUser(jsonObject);
        return response.getData();
    }

}

package top.tocchen.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tocchen.entity.AdminUser;
import top.tocchen.service.AdminUserSerivce;
import top.tocchen.utils.JsonFormatUtil;
import top.tocchen.utils.UserCaseAuthVoUtils;
import top.tocchen.utils.http.Response;
import top.tocchen.vo.AdminUserVo;

/**
 * @author tocchen
 * @date 2023/2/23 19:49
 * @since jdk 1.8
 **/
@RestController
@Api(tags = "管理员用户的操作")
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired private AdminUserSerivce adminUserSerivce;

    @ApiOperation(value = "注册管理员用户")
    @PostMapping("/register")
    public Response<?> registerAdminUser(@RequestBody JSONObject json){
        AdminUserVo adminUser = json.toJavaObject(AdminUserVo.class);
        JsonFormatUtil.validateJson2Data(adminUser);
        adminUserSerivce.insertAdminUser(adminUser);
        return Response.success();
    }


    @ApiOperation(value = "查询管理员用户")
    @PostMapping("/query")
    public Response<?> queryAdminUser(@RequestBody JSONObject json){
        AdminUserVo adminUser = json.toJavaObject(AdminUserVo.class);
        JsonFormatUtil.validateJson2Data(adminUser);
        AdminUser result = adminUserSerivce.queryAdminByAccountAndJobNumber(adminUser);
        return Response.success(UserCaseAuthVoUtils.use2Auth(result));
    }

}

package top.tocchen.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tocchen.entity.NormalUser;
import top.tocchen.service.NormalUserService;
import top.tocchen.utils.JsonFormatUtil;
import top.tocchen.utils.UserCaseAuthVoUtils;
import top.tocchen.utils.http.Response;
import top.tocchen.vo.NormalUserVo;

import java.util.regex.Pattern;

/**
 * @author tocchen
 * @date 2023/2/23 19:50
 * @since jdk 1.8
 **/
@RestController
@RequestMapping("/normal/user")
@Api(tags = "普通用户的操作")
public class NormalUserController {

    @Autowired
    private NormalUserService normalUserService;



    @ApiOperation(value = "注册用户")
    @PostMapping("/register")
    public Response<?> registerNormalUser(@RequestBody JSONObject json){
        NormalUserVo user = json.toJavaObject(NormalUserVo.class);
        JsonFormatUtil.validateJson2Data(user);
        normalUserService.insertNormalUser(user);
        return Response.success();
    }


    @ApiOperation(value = "查询用户")
    @PostMapping("/query")
    public Response<?> queryNormalUserByPhoneOrAccount(@RequestBody JSONObject json){
        NormalUserVo user = json.toJavaObject(NormalUserVo.class);
        JsonFormatUtil.validateJson2Data(user);
        NormalUser result = normalUserService.queryNormalUserByPhoneOrAccount(user);
        return Response.success(UserCaseAuthVoUtils.use2Auth(result));
    }

}

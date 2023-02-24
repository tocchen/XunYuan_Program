package top.tocchen.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tocchen.entity.CompanyUser;
import top.tocchen.service.CompanyUserService;
import top.tocchen.utils.JsonFormatUtil;
import top.tocchen.utils.UserCaseAuthVoUtils;
import top.tocchen.utils.http.Response;
import top.tocchen.vo.CompanyUserVo;

/**
 * @author tocchen
 * @date 2023/2/23 19:50
 * @since jdk 1.8
 **/
@RestController
@Api(tags = "企业用户的操作")
@RequestMapping("/company/user")
public class CompanyUserController {

    @Autowired CompanyUserService companyUserService;

    @ApiOperation(value = "注册企业用户")
    @PostMapping("/register")
    public Response<?> registerComapnyUser(@RequestBody JSONObject json){
        CompanyUserVo comUser = json.toJavaObject(CompanyUserVo.class);
        JsonFormatUtil.validateJson2Data(comUser);
        companyUserService.insertCompanyUser(comUser);
        return Response.success();
    }


    @ApiOperation(value = "查询企业用户")
    @PostMapping("/query")
    public Response<?> queryCompanyUser(@RequestBody JSONObject json){
        CompanyUserVo comUser = json.toJavaObject(CompanyUserVo.class);
        JsonFormatUtil.validateJson2Data(comUser);
        CompanyUser result = companyUserService.queryCompanyUserByPhone(comUser);
        return Response.success(UserCaseAuthVoUtils.use2Auth(result));
    }

    @ApiOperation(value = "密钥查询用户数据")
    @PostMapping("/query/sgin")
    public Response<?> queryCompanyUserBySgin(@RequestBody String sgin){
        return Response.success();
    }

}

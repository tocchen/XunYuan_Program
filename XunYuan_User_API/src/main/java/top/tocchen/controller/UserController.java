//package top.tocchen.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import io.swagger.annotations.*;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import top.tocchen.entity.AdminUser;
//import top.tocchen.utils.MD5Util;
//import top.tocchen.utils.UUIDUtil;
//import top.tocchen.utils.exceptions.DataFormatException;
//import top.tocchen.utils.exceptions.JSONFormatException;
//import top.tocchen.utils.exceptions.UpdateException;
//import top.tocchen.utils.http.Response;
//
//import java.util.HashMap;
//
///**
// * @author tocchen
// * @date 2023/2/9 22:12
// * @since jdk 1.8
// **/
//@Api(tags = "系统管理员用户操作")
//@Slf4j
//@RestController
//@RequestMapping("/manger/user")
//public class UserController {
//
//    @Autowired
//    private UserServiceImpl userService;
//
//    /**
//     * 登录请求
//     * @param loginJOSN 登录JSON对象 包含User基本信息 [email,jobNumber,password]
//     * @return 请求结果
//     */
//    @ApiOperation(value = "用户登录请求")
//    @PostMapping("/login")
//    public Response<?> userLogin(@RequestBody JSONObject loginJOSN){
//        AdminUser loginUser = loginJOSN.toJavaObject(AdminUser.class);
//        if (!AdminUser.userBaseComplete(loginUser)) {
//            throw new JSONFormatException();
//        }
//
//        AdminUser user = userService.queryMoreByUser(
//                loginUser.getJobNumber(), loginUser.getEmail(), loginUser.getPassword());
//        log.info("[ User-- Email:"+user.getEmail()+",Job Number:"+user.getJobNumber()+"login success! ]");
//        return Response.success(user);
//    }
//
//    /**
//     * 保存用户请求 一个工号只能存在一个账户
//     * @param userJSON 注册JSON对象 包含User基本信息 [email,jobNumber,password]
//     * @return 请求结果
//     */
//    @ApiOperation(value = "用户注册请求")
//    @PostMapping("/save")
//    public Response<?> saveUser(@RequestBody JSONObject userJSON){
//        AdminUser user = userJSON.toJavaObject(AdminUser.class);
//        if (!AdminUser.userBaseComplete(user)) {
//            throw new JSONFormatException();
//        }
//        HashMap<String, String> passwordMD5Map = MD5Util.generateMD5(user.getPassword());
//        userService.saveUser(
//                UUIDUtil.generateUUID(),user.getJobNumber(),user.getEmail(),passwordMD5Map.get("MD5Str"),passwordMD5Map.get("salt"));
//        log.info("[ User-- Email:"+user.getEmail()+",Job Number:"+user.getJobNumber()+"save success! ]");
//        return Response.success();
//    }
//
//    @ApiOperation(value = "用户注销(删除)请求")
//    @DeleteMapping("/delete/{id}")
//    public Response<?> deleteUser(@PathVariable("id") String id){
//        // 判断UUID长度是否符合规范
//        if (id.length() != UUIDUtil.UUID_LENGTH){
//            throw new DataFormatException();
//        }
//        if (userService.removeUser(id) == 0){
//            throw new UpdateException();
//        }
//        log.info("[ User-- id:"+id+"remove success! ]");
//        return Response.success();
//    }
//}

//package top.tocchen.feign;
//
//import com.alibaba.fastjson.JSONObject;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import top.tocchen.data.ServerNameData;
//import top.tocchen.utils.http.Response;
//
///**
// * @author tocchen
// * @date 2023/2/24 11:46
// * @since jdk 1.8
// **/
//@FeignClient(name = ServerNameData.XUNYUAN_USER_CV,path = "/company/user")
//public interface CompanyUserFeign {
//
//    @PostMapping("/query")
//    public Response<?> queryCompanyUser(@RequestBody JSONObject json);
//
//    @PostMapping("/query/sgin")
//    public Response<?> queryCompanyUserBySgin(@RequestBody String sgin);
//}

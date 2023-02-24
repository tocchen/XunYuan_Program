package top.tocchen.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.tocchen.utils.http.Response;

/**
 * @author tocchen
 * @date 2023/2/24 16:43
 * @since jdk 1.8
 **/
@FeignClient(name = "XunYuan-User-Service",path = "/normal/user")
public interface NormalUserFeign {

    @PostMapping("/query")
    public Response<?> queryNormalUserByPhoneOrAccount(@RequestBody JSONObject json);
}

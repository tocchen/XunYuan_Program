package top.tocchen.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import top.tocchen.entity.CVLogEntity;
import top.tocchen.service.CVLogService;
import top.tocchen.utils.exceptions.JSONFormatException;
import top.tocchen.utils.http.Response;


/**
 * @author tocchen
 * @date 2023/2/20 21:02
 * @since jdk 1.8
 **/
@RestController
@RequestMapping("/cv/log")
public class CVLogController {

    @Autowired
    private CVLogService service;

    @PostMapping("/add")
    public Response<?> addCVLog(@RequestBody JSONObject json){
        CVLogEntity cvLog = json.toJavaObject(CVLogEntity.class);
        if (ObjectUtils.isEmpty(cvLog)){
            throw new JSONFormatException();
        }
        service.insertCVLog(cvLog);
        return Response.success();
    }

    @GetMapping("/query/company/{current}/{pageSize}/{id}")
    public Response<?> queryByCompanyIdCVLog(@PathVariable("id") String companyId,
                                             @PathVariable("current") int current,
                                             @PathVariable("pageSize") int pageSize){

        return Response.success(service.queryAllByCompanyId(companyId, current, pageSize));
    }

    @GetMapping("/query/user/{current}/{pageSize}/{id}")
    public Response<?> queryByUserIdCVLog(@PathVariable("id") String companyId,
                                          @PathVariable("current") int current,
                                          @PathVariable("pageSize") int pageSize){

        return Response.success(service.queryAllByUserId(companyId, current, pageSize));
    }

    @GetMapping("/query/all/{current}/{pageSize}")
    public Response<?> queryAllCVLog(@PathVariable("current") int current,
                                     @PathVariable("pageSize") int pageSize){
        return Response.success(service.queryAll(current,pageSize));
    }

    @DeleteMapping("/delete/{id}")
    public Response<?> queryAllCVLog(@PathVariable("id") String id){
        service.removeCVLog(id);
        return Response.success();
    }
}

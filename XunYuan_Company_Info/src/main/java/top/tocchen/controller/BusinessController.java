package top.tocchen.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import top.tocchen.entity.BusinessEntity;
import top.tocchen.entity.CompanyInfoEntity;
import top.tocchen.service.BusinessService;
import top.tocchen.service.CompanyInfoService;
import top.tocchen.utils.exceptions.ExecuteException;
import top.tocchen.utils.exceptions.JSONFormatException;
import top.tocchen.utils.exceptions.QueryException;
import top.tocchen.utils.http.Response;
import top.tocchen.vo.CompanyInfoVo;

/**
 * @author tocchen
 * @date 2023/2/19 20:12
 * @since jdk 1.8
 **/
@RestController
@RequestMapping("/business")
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    @PostMapping("/save")
    public Response<?> saveBusiness(@RequestBody JSONObject JSONStr){
        BusinessEntity entity = JSONStr.toJavaObject(BusinessEntity.class);
        if (ObjectUtils.isEmpty(entity)){
            throw new JSONFormatException();
        }
        businessService.saveBusiness(entity);
        return Response.success();
    }

    @GetMapping("/query/{id}")
    public Response<?> queryBusinessInfoById(@PathVariable("id") String id){
        BusinessEntity businessEntity = businessService.queryBusinessById(id);
        if (ObjectUtils.isEmpty(businessEntity)){
            throw new QueryException();
        }
        return Response.success(businessEntity);
    }

    @DeleteMapping("/delete/{id}")
    public Response<?> deletedBusinessInfoById(@PathVariable("id") String id){
        Long result = businessService.deletedBusiness(id);
        if (result != 1){
            throw new ExecuteException();
        }
        return Response.success();
    }

    @PostMapping("/update")
    public Response<?> updateBusinessInfoByVo(@RequestBody JSONObject JSONStr){
        BusinessEntity entity = JSONStr.toJavaObject(BusinessEntity.class);
        if (ObjectUtils.isEmpty(entity)){
            throw new JSONFormatException();
        }
        Long result = businessService.updateBusinessById(entity);
        if (result != 1){
            throw new ExecuteException();
        }
        return Response.success();
    }

}

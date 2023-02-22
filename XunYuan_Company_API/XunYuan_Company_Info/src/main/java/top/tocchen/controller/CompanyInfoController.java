package top.tocchen.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

import java.util.List;

/**
 * @author tocchen
 * @date 2023/2/19 20:11
 * @since jdk 1.8
 **/
@RestController
@RequestMapping("/companyInfo")
public class CompanyInfoController {
    @Autowired
    private CompanyInfoService companyInfoService;
    @Autowired
    private BusinessService businessService;

    @PostMapping("/save")
    public Response<?> saveCompanyInfo(@RequestBody JSONObject JSONStr){
        CompanyInfoVo companyInfoVo = JSONStr.toJavaObject(CompanyInfoVo.class);
        if (ObjectUtils.isEmpty(companyInfoVo)){
            throw new JSONFormatException();
        }
        companyInfoService.saveCompanyInfo(companyInfoVo);
        return Response.success();
    }

    @GetMapping("/query/{id}")
    public Response<?> queryCompanyInfoById(@PathVariable("id") String id){
        CompanyInfoEntity companyInfoEntity = companyInfoService.queryCompanyById(id);
        if (ObjectUtils.isEmpty(companyInfoEntity)){
            throw new QueryException();
        }
        BusinessEntity businessEntity = businessService.queryBusinessById(companyInfoEntity.getBusinessId());
        if (ObjectUtils.isEmpty(businessEntity)){
            throw new QueryException();
        }
        CompanyInfoVo result = new CompanyInfoVo();
        result.setCompanyInfoVoData(companyInfoEntity,businessEntity);
        System.out.println(JSONObject.toJSON(result));
        return Response.success(result);
    }

    @GetMapping("/query}")
    public Response<?> queryCompanyByCompanyName(@RequestParam("companyName") String companyName){
        List<CompanyInfoEntity> result = companyInfoService.queryCompanyByName(companyName);
        return Response.success(result);
    }

    @DeleteMapping("/delete/{id}")
    public Response<?> deletedCompanyInfoById(@PathVariable("id") String id){
        Long result = companyInfoService.deletedCompanyInfoById(id);
        if (result != 1){
            throw new ExecuteException();
        }
        return Response.success();
    }

    @PostMapping("/update")
    public Response<?> updateCompanyInfoByVo(@RequestBody JSONObject JSONStr){
        CompanyInfoEntity entity = JSONStr.toJavaObject(CompanyInfoEntity.class);
        if (ObjectUtils.isEmpty(entity)){
            throw new JSONFormatException();
        }
        Long result = companyInfoService.updateCompanyInfoById(entity);
        if (result != 1){
            throw new ExecuteException();
        }
        return Response.success();
    }
}

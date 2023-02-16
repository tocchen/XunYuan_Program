package top.tocchen.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import top.tocchen.entity.CompanyEntity;
import top.tocchen.service.CompanyService;
import top.tocchen.service.impl.CompanyServiceImpl;
import top.tocchen.utils.DBUtil;
import top.tocchen.utils.UUIDUtil;
import top.tocchen.utils.exceptions.DataExistsException;
import top.tocchen.utils.exceptions.DataFormatException;
import top.tocchen.utils.http.Response;
import top.tocchen.vo.PageResponse;

import java.util.List;
import java.util.Random;

/**
 * @author tocchen
 * @date 2023/2/11 14:41
 * @since jdk 1.8
 **/
@RestController
@RequestMapping("/company")
@Slf4j
@Api(tags = "公司操作Controller")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @ApiOperation(value = "分页查询操作")
    @GetMapping("/pageQuery/{current}/{pageSize}")
    public Response<?> queryPageAllCompany(@PathVariable("current") int current,
                                           @PathVariable("pageSize") int pageSize){
        List<CompanyEntity> result = companyService.queryPageAllCompany(current, pageSize);
        int count =  companyService.queryPageCount(null);
        PageResponse pageResponse = new PageResponse(result, current, pageSize, count / pageSize,count);
        log.info("[分页查询成功,当前页："+current+" 查询总数为："+count+" 总页数为："+(count/pageSize+1)+"]");
        return Response.success(pageResponse);
    }

    @ApiOperation(value = "模糊查询-分页")
    @GetMapping("/pageQuery/{current}/{pageSize}/{parameter}")
    public Response<?> queryCompanyByParameter(@PathVariable("parameter") String parameter,
                                               @PathVariable("current") int current,
                                               @PathVariable("pageSize") int pageSize){
        List<CompanyEntity> result = companyService.queryCompanyLikeNameCodeAddress(parameter, current, pageSize);
        int count = companyService.queryPageCount(parameter);
        PageResponse pageResponse = new PageResponse(result, current, pageSize, count / pageSize, count);
        log.info("[分页查询成功,查询条件："+parameter+",当前页："+current+" 查询总数为："+count+" 总页数为："+count/pageSize+"]");
        return Response.success(pageResponse);
    }

    @ApiOperation(value = "删除公司")
    @DeleteMapping("/delete/{companyId}")
    public Response<?> removeCompanyById(@PathVariable("companyId") String companyId){
         DBUtil.numberNo1UpdateException(companyService.removeCompanyById(companyId));
         log.info("删除公司-ID:"+companyId);
         return Response.success();
    }

    @ApiOperation(value = "更新公司信息")
    @PostMapping("/update")
    public Response<?> updateCompanyById(@RequestBody JSONObject companyJson){
        CompanyEntity companyEntity = companyJson.toJavaObject(CompanyEntity.class);
        DBUtil.isEmpty2QueryException(companyEntity);
        if (companyEntity.getId() == null || "".equals(companyEntity.getId())){
            throw new DataFormatException();
        }
        DBUtil.numberNo1UpdateException(companyService.updateCompanyById(companyEntity));
        log.info("更新公司成功-公司ID:"+companyEntity.getId());
        return Response.success();
    }

    @ApiOperation(value = "修改公司锁定状态")
        @PostMapping("/changeStatus")
    public Response<?> updateCompanyStatusById(@RequestBody String id){
        DBUtil.numberNo1UpdateException(companyService.updateCompanyStatusById(id));
        log.info("公司锁定成功-公司ID:"+id);
        return Response.success();
    }

    @ApiOperation(value = "保存公司")
    @PostMapping("/saveCompany")
    public Response<?> saveCompany(@RequestBody JSONObject companyJSON){
        CompanyEntity companyEntity = companyJSON.toJavaObject(CompanyEntity.class);
        DBUtil.isEmpty2QueryException(companyEntity);
        companyEntity.setId(UUIDUtil.generateUUID());
        companyEntity.setSignKey(DigestUtils.md5DigestAsHex(
                (((Long)System.currentTimeMillis()).toString()+new Random().nextInt(100)).getBytes()));
        companyService.saveCompany(companyEntity);
        return Response.success();
    }

    @ApiOperation(value = "判断公司是否存在")
    @GetMapping("/query/exist/{companyName}")
    public Response<?> queryCompanyNameExist(@PathVariable("companyName") String companyName){
        if (companyService.queryCompanyNameExist(companyName) != 0){
            throw new DataExistsException();
        }else{
            return Response.success();
        }
    }
}

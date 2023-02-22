package top.tocchen.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.tocchen.entity.DataDictEntity;
import top.tocchen.service.DataDictService;
import top.tocchen.utils.http.Response;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author tocchen
 * @date 2023/2/13 18:20
 * @since jdk 1.8
 **/
@RestController
@RequestMapping("/dataDict")
@Slf4j
@Api(tags = "数据字典Controller")
public class DataDictController {

    @Autowired
    private DataDictService dataDictService;

    @ApiOperation(value = "通过parentId获取其子项")
    @GetMapping("/search/{parentId}")
    public Response<?> searchByParentId(@PathVariable String parentId){
        List<DataDictEntity> result = dataDictService.queryByParentId(Integer.parseInt(parentId));
        return Response.success(result);
    }

    @GetMapping("/export/data")
    public void exportData(HttpServletResponse response){
        dataDictService.exportData(response);
    }

    @PostMapping("/import/data")
    public Response<?> importData(@RequestParam("dataDictUpload") MultipartFile multipartFile){
        dataDictService.importData(multipartFile);
        return Response.success();
    }

}

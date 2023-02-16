package top.tocchen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.tocchen.feign.DataDictFeign;
import top.tocchen.utils.http.Response;

import javax.servlet.http.HttpServletResponse;

/**
 * @author tocchen
 * @date 2023/2/15 18:00
 * @since jdk 1.8
 **/
@RestController
@RequestMapping("/dataDict")
public class DataDictController {

    @Autowired
    private DataDictFeign dataDictFeign;

    @GetMapping("/search/{parentId}")
    public Response<?> searchByParentId(@PathVariable("parentId") String parentId){
        return dataDictFeign.searchByParentId(parentId);
    }

    @GetMapping("/export/data")
    public void exportData(HttpServletResponse response){
        dataDictFeign.exportData(response);
    }

    @PostMapping("/import/data")
    public Response<?> importData(@RequestParam("dataDictUpload") MultipartFile multipartFile){
        return dataDictFeign.importData(multipartFile);
    }

}

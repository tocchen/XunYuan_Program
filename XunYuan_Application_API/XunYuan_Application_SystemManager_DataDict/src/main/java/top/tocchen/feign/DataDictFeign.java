package top.tocchen.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.tocchen.utils.http.Response;
import top.tocchen.vo.DataDictEntity;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;

/**
 * @author tocchen
 * @date 2023/2/15 17:53
 * @since jdk 1.8
 **/
@FeignClient(value = "XunYuan-SystemManager-DataDict-Service",path = "/dataDict")
public interface DataDictFeign {
    @GetMapping("/search/{parentId}")
    Response<?> searchByParentId(@PathVariable("parentId") String parentId);

    @GetMapping("/export/data")
    void exportData(HttpServletResponse response);

    @PostMapping(value = "/import/data",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Response<?> importData(@RequestPart("dataDictUpload") MultipartFile multipartFile);
}

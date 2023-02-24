package top.tocchen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.tocchen.entity.WorkEntity;
import top.tocchen.service.WorkService;
import top.tocchen.utils.exceptions.UpdateException;
import top.tocchen.utils.http.Response;


/**
 * @author tocchen
 * @date 2023/2/20 11:53
 * @since jdk 1.8
 **/
@RestController
@RequestMapping("/work/setting")
public class WorkSettingController {

    @Autowired
    private WorkService workService;

    // TODO 没有使用JSON获取数据
    @PostMapping("/add")
    public Response<?> addWork(@RequestBody WorkEntity workEntity){
        workService.addWork(workEntity);
        return Response.success();
    }

    @DeleteMapping("/deleted/{id}/{companyId}")
    public Response<?> deletedWork(@PathVariable("id") String id,@PathVariable("companyId") String companyId){
        Long result = workService.removeWorkByIdAndComId(id, companyId);
        if (result != 1){
            throw new UpdateException();
        }
        return Response.success();
    }

    @PostMapping("/update")
    public Response<?> updateWork(@RequestBody WorkEntity workEntity){
        Long result = workService.updateWorkById(workEntity);
        if (result != 1){
            throw new UpdateException();
        }
        return Response.success();
    }

}

package top.tocchen.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import top.tocchen.entity.WorkEntity;
import top.tocchen.service.WorkService;
import top.tocchen.utils.DBUtil;
import top.tocchen.utils.http.Response;
import top.tocchen.vo.PageResponse;

import java.util.List;

/**
 * @author tocchen
 * @date 2023/2/20 12:14
 * @since jdk 1.8
 **/
@RestController
@RequestMapping("/work/get")
public class WorkGetController {

    @Autowired
    private WorkService workService;

    @GetMapping("/{id}")
    public Response<?> getWorkById(@PathVariable("id") String id){
        WorkEntity workEntity = workService.queryWorkById(id);
        DBUtil.isEmpty2QueryException(workEntity);
        return Response.success(workEntity);
    }

    @GetMapping("/latest/{pageSize}/{current}")
    public Response<?> getWorkById(@PathVariable("pageSize") int pageSize,@PathVariable("current") int current){
        List<WorkEntity> workEntities = workService.queryLatestWorkPage(current, pageSize);
        DBUtil.isEmpty2QueryException(workEntities);
        PageResponse<List> result = new PageResponse<List>(workEntities,current,pageSize,0,0);
        return Response.success(result);
    }

    @GetMapping("/query/details/{pageSize}/{current}")
    public Response<?> queryWorkByParam(@RequestParam("param") String param,@PathVariable("pageSize") int pageSize,@PathVariable("current") int current){
        List<WorkEntity> result = workService.queryWorkByParamPage(param, pageSize, current);
        PageResponse<List> resultPage = new PageResponse<List>(result,current,pageSize,0,0);
        return Response.success(resultPage);
    }

    @GetMapping("/query/screening/{pageSize}/{current}")
    public Response<?> queryWorkByScreeningParam(@RequestBody(required = false)JSONObject json,@PathVariable("pageSize") int pageSize,@PathVariable("current") int current){
        String address = json.getString("address");
        String educational = json.getString("educational");
        Double starWorkSalary = json.getObject("starWorkSalary", Double.class);
        Double endWorkSalary = json.getObject("endWorkSalary", Double.class);
        if (ObjectUtils.isEmpty(endWorkSalary)){
            endWorkSalary = 0.0;
        }
        String workType = json.getString("workType");
        List<WorkEntity> result = workService.queryWorkByScreeningParam(address, starWorkSalary, endWorkSalary, educational, workType, current, pageSize);
        PageResponse<List> resultPage = new PageResponse<List>(result,current,pageSize,0,0);
        return Response.success(resultPage);
    }
}

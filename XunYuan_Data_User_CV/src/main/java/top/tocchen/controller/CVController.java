package top.tocchen.controller;

import top.tocchen.entity.CVEntity;
import top.tocchen.enums.UserCVUpdateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.tocchen.service.ICVService;
import top.tocchen.utils.exceptions.ExecuteException;
import top.tocchen.utils.exceptions.UpdateException;
import top.tocchen.utils.http.Response;

/**
 * @author tocchen
 * @date 2023/2/20 14:21
 * @since jdk 1.8
 **/
@RestController
@RequestMapping("/cv")
public class CVController {

    @Autowired
    private ICVService icvService;

    @PostMapping("/add")
    public Response<?> addCV(@RequestBody CVEntity cvEntity){
        String id = icvService.saveCV(cvEntity);
        return Response.success(id);
    }

    @PostMapping("/update/advantage")
    public Response<?> updateUserAdvantageById(@RequestBody CVEntity cvEntity){
        Long result = icvService.updateCVById(UserCVUpdateType.ADVANTAGE, cvEntity.getUserAdvantage(), cvEntity.getId());
        if (result != 1){
            throw new UpdateException();
        }
        return Response.success();
    }

    @PostMapping("/update/educational")
    public Response<?> updateUserEducationalById(@RequestBody CVEntity cvEntity){
        Long result = icvService.updateCVById(UserCVUpdateType.EDUCATIONAL, cvEntity.getUserEducational(), cvEntity.getId());
        if (result != 1){
            throw new UpdateException();
        }
        return Response.success();
    }
    @PostMapping("/update/info")
    public Response<?> updateUserInfoById(@RequestBody CVEntity cvEntity){
        Long result = icvService.updateCVById(UserCVUpdateType.INFO, cvEntity.getUserInfo(), cvEntity.getId());
        if (result != 1){
            throw new UpdateException();
        }
        return Response.success();
    }
    @PostMapping("/update/projectHistory")
    public Response<?> updateUserProjectHistoryById(@RequestBody CVEntity cvEntity){
        Long result = icvService.updateCVById(UserCVUpdateType.PROJECT_HISTORY, cvEntity.getUserProjectHistory(), cvEntity.getId());
        if (result != 1){
            throw new UpdateException();
        }
        return Response.success();
    }
    @PostMapping("/update/workHistory")
    public Response<?> updateUserWorkHistoryById(@RequestBody CVEntity cvEntity){
        Long result = icvService.updateCVById(UserCVUpdateType.WORK_HISTORY, cvEntity.getUserWorkHistory(), cvEntity.getId());
        if (result != 1){
            throw new UpdateException();
        }
        return Response.success();
    }

    @DeleteMapping("/delete/{id}")
    public Response<?> deleteCVById(@PathVariable("id") String id){
        Long result = icvService.removeCVById(id);
        if (result != 1){
            throw new ExecuteException();
        }
        return Response.success();
    }

    @GetMapping("/get/{id}")
    public Response<?> getCVById(@PathVariable("id") String id){
        CVEntity cvEntity = icvService.queryCVById(id);
        return Response.success(cvEntity);
    }
}

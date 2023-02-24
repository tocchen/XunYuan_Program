package top.tocchen.utils.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.tocchen.utils.http.HttpStatusEnum;
import top.tocchen.utils.http.Response;

import java.util.zip.DataFormatException;


/**
 * @author tocchen
 * @date 2023/2/9 21:42
 * @since jdk 1.8
 **/
@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    /**
     * 查询异常
     * @param queryException queryException
     * @return Response
     */
    @ExceptionHandler(QueryException.class)
    public Response<?> queryExceptionHandler(QueryException queryException){
        log.error(queryException.getMessage());
        return Response.fail(HttpStatusEnum.PRECONDITION_FAIL);
    }

    /**
     * 更新异常
     * @param updateException updateException
     * @return Response
     */
    @ExceptionHandler(UpdateException.class)
    public Response<?> updateExceptionHandler(UpdateException updateException){
        log.error(updateException.getMessage());
        return Response.fail(HttpStatusEnum.PRECONDITION_FAIL);
    }

    /**
     * JSON格式异常
     * @param jsonFormatException jsonFormatException
     * @return Response
     */
    @ExceptionHandler(JSONFormatException.class)
    public Response<?> jsonFormatExceptionHandler(JSONFormatException jsonFormatException){
        log.error(jsonFormatException.getMessage());
        return Response.fail(HttpStatusEnum.PRECONDITION_FAIL);
    }

    /**
     * 数据格式异常
     * @param dataFormatException dataFormatException
     * @return Response
     */
    @ExceptionHandler(DataFormatException.class)
    public Response<?> dataFormatExceptionHandler(DataFormatException dataFormatException){
        log.error(dataFormatException.getMessage());
        return Response.fail(HttpStatusEnum.PRECONDITION_FAIL);
    }

    /**
     * 数据已存在异常
     * @param dataExistsException dataExistsException
     * @return Response
     */
    @ExceptionHandler(DataExistsException.class)
    public Response<?> dataExistsExceptionHandler(DataExistsException dataExistsException){
        log.error(dataExistsException.getMessage());
        return Response.fail(HttpStatusEnum.DATA_EXISTS);
    }

    /**
     * 算术异常
     * @param arithmeticException arithmeticException
     * @return Response
     */
    @ExceptionHandler(ArithmeticException.class)
    public Response<?> arithmeticExceptionHandler(ArithmeticException arithmeticException){
        log.error(arithmeticException.getMessage());
        return Response.fail(HttpStatusEnum.REQUEST_FAIL);
    }

    /**
     * 执行异常
     * @param executeException executeException
     * @return Response
     */
    @ExceptionHandler(ExecuteException.class)
    public Response<?> executeExceptionHandler(ExecuteException executeException){
        log.error(executeException.getMessage());
        return Response.fail(HttpStatusEnum.REQUEST_FAIL);
    }


    /**
     * JSON 参数错误 无法获取准确的值
     * @param e e
     * @return Response
     */
    @ExceptionHandler(JsonParamFailException.class)
    public Response<?> jsonParamFailExceptionHandler(JsonParamFailException e){
        log.error(e.getMessage());
        return Response.fail(HttpStatusEnum.REQUEST_PARAM_FAIL);
    }

    /**
     * 用户注册失败 原有：用户已存在
     * @param e e
     * @return Response
     */
    @ExceptionHandler(UserExistNotRegisterException.class)
    public Response<?> userExistNotRegisterExceptionHandler(UserExistNotRegisterException e){
        log.error(e.getMessage());
        return Response.fail(HttpStatusEnum.DATA_EXISTS);
    }
}

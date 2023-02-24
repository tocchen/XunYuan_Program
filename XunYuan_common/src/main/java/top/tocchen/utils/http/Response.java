package top.tocchen.utils.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author tocchen
 * @date 2023/2/9 19:11
 * @since jdk 1.8
 * 后端统一返回值 与 HttpStatusEnum枚举联合使用
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 20230215L;

    /** 返回数据 */
    private T data;
    /** 返回状态码 */
    private int code;
    /** 返回消息 */
    private String message;

    private String token;

    public Response (T data, int code, String message){
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public Response (int code,String message){
        this(null,code,message);
    }

    public Response (T data, HttpStatusEnum httpStatusEnum){
        this(data,httpStatusEnum.getCode(),httpStatusEnum.getMessage());
    }

    public Response (HttpStatusEnum httpStatusEnum){
        this(null,httpStatusEnum);
    }

    /**
     * 成功返回 :: 默认200，success
     * @return Response
     */
    public static Response<?> success(){
        return new Response<Object>(HttpStatusEnum.REQUEST_SUCCESS);
    }
    /**
     * 成功返回
     * @return Response
     */
    public static Response<?> success(HttpStatusEnum httpStatusEnum){
        return new Response<Object>(httpStatusEnum);
    }
    /**
     * 成功返回
     * @return Response
     */
    public static <E> Response<E> success(E data){
        return new Response<E>(data,HttpStatusEnum.REQUEST_SUCCESS);
    }
    /**
     * 成功返回
     * @return Response
     */
    public static <E> Response<E> success(E data,HttpStatusEnum httpStatusEnum){
        return new Response<E>(data, httpStatusEnum);
    }

    /**
     * 请求失败
     * @return Response
     */
    public static Response<?> fail(){
        return new Response<Object>(HttpStatusEnum.REQUEST_FAIL);
    }
    /**
     * 请求失败
     * @return Response
     */
    public static Response<?> fail(HttpStatusEnum httpStatusEnum){
        return new Response<Object>(httpStatusEnum);
    }
    /**
     * 请求失败
     * @return Response
     */
    public static <E> Response<E> fail(E data){
        return new Response<E>(data,HttpStatusEnum.REQUEST_FAIL);
    }
    /**
     * 请求失败
     * @return Response
     */
    public static <E> Response<E> fail(E data, HttpStatusEnum httpStatusEnum){
        return new Response<E>(data,HttpStatusEnum.REQUEST_FAIL);
    }


}

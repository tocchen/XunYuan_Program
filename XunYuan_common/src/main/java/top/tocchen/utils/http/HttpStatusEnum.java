package top.tocchen.utils.http;


import lombok.Getter;

/**
 * @author tocchen
 * @date 2023/2/9 19:26
 * @since jdk 1.8
 **/
@Getter
public enum HttpStatusEnum {

    /** 请求成功 */
    REQUEST_SUCCESS(200,"success"),
    /** 请求失败 */
    REQUEST_FAIL(400,"Bad Request"),
    /** 客户端请求信息的先决条件错误 */
    PRECONDITION_FAIL(412,"Precondition Failed"),
    /** 数据已存在 */
    DATA_EXISTS(413,"Data Already Exists");

    private int code;
    private String message;

    HttpStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

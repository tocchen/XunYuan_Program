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
    REQUEST_FAIL(400,"fail");

    private int code;
    private String message;

    HttpStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

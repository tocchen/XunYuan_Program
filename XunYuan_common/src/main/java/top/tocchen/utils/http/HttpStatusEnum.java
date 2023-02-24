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
    /** 参数错误 */
    REQUEST_PARAM_FAIL(420,"Bad Request Param"),
    /** 用户未登录，或Token信息失效 */
    USER_AUTH_FAIL(421,"The user does not login or the Token information is invalid "),
    /** 用户权限认证失败 */
    USER_ROLE_AUTH_FAIL(422,"User permission authentication failed"),
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

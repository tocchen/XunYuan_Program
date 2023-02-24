package top.tocchen.utils.exceptions;

import java.io.Serializable;

/**
 * @author tocchen
 * @date 2023/2/23 20:16
 * @since jdk 1.8
 **/
public class JsonParamFailException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -862626148791196921L;

    public JsonParamFailException() {
        super("JSON参数错误，无法获取对应数据");
    }
}

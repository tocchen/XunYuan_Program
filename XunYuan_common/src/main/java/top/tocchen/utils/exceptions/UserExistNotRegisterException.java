package top.tocchen.utils.exceptions;

/**
 * @author tocchen
 * @date 2023/2/23 20:43
 * @since jdk 1.8
 **/
public class UserExistNotRegisterException extends RuntimeException {
    private static final long serialVersionUID = -4548538191542899629L;

    public UserExistNotRegisterException() {
        super("用户注册失败 原有：用户已存在");
    }
}

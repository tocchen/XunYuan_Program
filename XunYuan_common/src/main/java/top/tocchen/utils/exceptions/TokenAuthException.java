package top.tocchen.utils.exceptions;

/**
 * @author tocchen
 * @date 2023/2/22 19:22
 * @since jdk 1.8
 **/
public class TokenAuthException extends RuntimeException {
    public TokenAuthException(){
        super("token认证失败");
    }
}

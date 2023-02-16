package top.tocchen.utils.exceptions;

/**
 * @author tocchen
 * @date 2023/2/9 22:08
 * @since jdk 1.8
 * 更新失败
 **/
public class UpdateException extends RuntimeException {
    public UpdateException(){
        super("更新失败");
    }
}

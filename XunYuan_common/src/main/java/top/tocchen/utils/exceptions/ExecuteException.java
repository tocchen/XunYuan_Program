package top.tocchen.utils.exceptions;

/**
 * @author tocchen
 * @date 2023/2/14 14:36
 * @since jdk 1.8
 **/
public class ExecuteException extends RuntimeException {
    public ExecuteException(){
        super("执行错误");
    }
}

package top.tocchen.utils.exceptions;

/**
 * @author tocchen
 * @date 2023/2/10 16:26
 * @since jdk 1.8
 **/
public class DataExistsException extends RuntimeException {
    public DataExistsException(){
        super("数据已存在");
    }
}

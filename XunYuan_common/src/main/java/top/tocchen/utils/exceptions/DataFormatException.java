package top.tocchen.utils.exceptions;

/**
 * @author tocchen
 * @date 2023/2/10 18:06
 * @since jdk 1.8
 **/
public class DataFormatException extends RuntimeException {
    public DataFormatException(){
        super("数据格式错误");
    }
}

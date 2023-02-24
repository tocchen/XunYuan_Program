package top.tocchen.utils.exceptions;

/**
 * @author tocchen
 * @date 2023/2/10 18:06
 * @since jdk 1.8
 **/
public class DataFormatException extends RuntimeException {
    public DataFormatException(){
        super("请求失败：数据格式错误（某个必要参数为空，或参数内容不正确）");
    }
}

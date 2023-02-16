package top.tocchen.utils.exceptions;

/**
 * @author tocchen
 * @date 2023/2/9 22:16
 * @since jdk 1.8
 * JSON 格式错误
 **/
public class JSONFormatException extends RuntimeException {
    public JSONFormatException(){
        super("JSON格式错误");
    }
}

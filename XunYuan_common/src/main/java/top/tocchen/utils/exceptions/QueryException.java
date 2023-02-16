package top.tocchen.utils.exceptions;

/**
 * @author tocchen
 * @date 2023/2/9 21:56
 * @since jdk 1.8
 * 查询失败
 **/
public class QueryException extends RuntimeException {
    public QueryException(){
        super("查询失败");
    }
}

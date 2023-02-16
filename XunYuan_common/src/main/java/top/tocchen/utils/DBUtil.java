package top.tocchen.utils;

import org.springframework.util.ObjectUtils;
import top.tocchen.utils.exceptions.QueryException;
import top.tocchen.utils.exceptions.UpdateException;

/**
 * @author tocchen
 * @date 2023/2/9 21:59
 * @since jdk 1.8
 **/
public class DBUtil {

    /**
     * 判断对象是否为空，为空则抛出{@link top.tocchen.utils.exceptions.QueryException}
     * @param o 对象
     * @param <E> 对象类型
     */
    public static <E> void isEmpty2QueryException(E o){
        if (ObjectUtils.isEmpty(o)){
            throw new QueryException();
        }else if( o instanceof String){
            if (((String) o).length() == 0){
                throw new QueryException();
            }
        }
    }

    /**
     * 判断数字值是否为1，否则抛出{@link top.tocchen.utils.exceptions.UpdateException}
     * @param num 数字值
     */
    public static void numberNo1UpdateException(int num){
        if (num != 1){
            throw new UpdateException();
        }
    }

}

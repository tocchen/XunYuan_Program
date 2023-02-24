package top.tocchen.utils;

import top.tocchen.utils.exceptions.JsonParamFailException;

/**
 * @author tocchen
 * @date 2023/2/23 20:14
 * @since jdk 1.8
 **/
public class JsonFormatUtil {

    public static <E> void validateJson2Data(E data){
        if (data == null){
            throw new JsonParamFailException();
        }
    }

}

package top.tocchen.utils;

import java.util.UUID;

/**
 * @author tocchen
 * @date 2023/2/10 15:17
 * @since jdk 1.8
 * UUID 主键生成工具类
 **/
public class UUIDUtil {
    /** UUID 长度 */
    public static final int UUID_LENGTH = 10;
    /**
     * 生成UUID
     * @return 返回UUID字符串
     */
    public static String generateUUID(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-","").substring(0,UUID_LENGTH);
    }

}

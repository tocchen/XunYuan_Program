package top.tocchen.utils;

import com.sun.istack.internal.NotNull;
import org.springframework.util.DigestUtils;
import sun.security.provider.MD5;

import java.util.HashMap;
import java.util.Random;

/**
 * @author tocchen
 * @date 2023/2/10 15:24
 * @since jdk 1.8
 * MD5 加密工具类
 **/
public class MD5Util {
    /** Slat 字典数组 */
    private static final char[] SALT_DICTIONARY = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*?:.,".toCharArray();
    /** Salt 基本长度 */
    private static final int SALT_BASE_LENGTH = 4;
    /** Salt 长度上线 是 Salt基本长度的两倍 */
    private static final int SALT_MAX_LENGTH = SALT_BASE_LENGTH << 1;

    /**
     * 将字符串转换为md5
     * @param str str
     * @return <MD5Str,value> AND <salt,value>
     */
    public static HashMap<String,String> generateMD5(@NotNull String str){
        return generateMD5(str,randomSalt());
    }
    /**
     * 将字符串转换为md5
     * @param str str
     * @param salt 机密盐
     * @return <MD5Str,value> AND <salt,value>
     */
    public static HashMap<String,String> generateMD5(@NotNull String str,@NotNull String salt){
        HashMap<String, String> result = new HashMap<String, String>();
        result.put("MD5Str",DigestUtils.md5DigestAsHex((str+salt).getBytes()));
        result.put("salt",salt);
        return result;
    }

    /**
     * 比较MD5值是否相同
     * @param md5 MD5值
     * @param str 字符串
     * @param salt 加密盐
     * @return 比较结果
     */
    public static boolean equalsMD5(@NotNull String md5,@NotNull String str,@NotNull String salt){
        return md5.equals(DigestUtils.md5DigestAsHex((str+salt).getBytes()));
    }

    /**
     * 随机生成加密盐 上限为SALT_MAX_LENGTH 下限为SALT_BASE_LENGTH
     * @return 加密盐
     */
    private static String randomSalt(){
        int saltLength = new Random().nextInt(SALT_MAX_LENGTH)+SALT_BASE_LENGTH;
        StringBuilder builder = new StringBuilder(saltLength);
        for (int i = 0; i < saltLength; i++) {
            builder.append(SALT_DICTIONARY[new Random().nextInt(SALT_DICTIONARY.length)]);
        }
        return builder.toString();
    }

}

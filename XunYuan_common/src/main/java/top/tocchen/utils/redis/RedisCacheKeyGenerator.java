package top.tocchen.utils.redis;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;


/**
 * @author tocchen
 * @date 2023/2/21 17:48
 * @since jdk 1.8
 *
 * Redis Cache Configuration
 **/
@Configuration
public class RedisCacheKeyGenerator {

    /** 单个结果的Key前缀 */
    public static final String RESULT_PREP = "Result-By";
    /** 总数的Key前缀 */
    public static final String COUNT_PREP = "Count-By";
    /** 分页查询的Key前缀 */

    @Bean("queryKeyGenerator")
    public KeyGenerator queryKeyGenerator(){
        return new KeyGenerator(){
            @Override
            public Object generate(Object target, Method method, Object... params) {
                return method.getName()+Arrays.asList(params).toString();
            }
        };
    }

    @Bean("queryByIdKeyGenerator")
    public KeyGenerator queryByIdKeyGenerator(){
        return new KeyGenerator(){
            @Override
            public Object generate(Object target, Method method, Object... params) {
                return RESULT_PREP+Arrays.asList(params).toString();
            }
        };
    }

    @Bean("removeByIdKeyGenerator")
    public KeyGenerator removeByIdKeyGenerator(){
        return new KeyGenerator(){
            @Override
            public Object generate(Object target, Method method, Object... params) {
                return RESULT_PREP+Arrays.asList(params).toString();
            }
        };
    }

    @Bean("queryAllCountKeyGenerator")
    public KeyGenerator queryAllCountKeyGenerator(){
        return new KeyGenerator(){
            @Override
            public Object generate(Object target, Method method, Object... params) {
                return COUNT_PREP+method.getName();
            }
        };
    }

    @Bean("queryByParamCountKeyGenerator")
    public KeyGenerator queryByParamCountKeyGenerator(){
        return new KeyGenerator(){
            @Override
            public Object generate(Object target, Method method, Object... params) {
                return COUNT_PREP+method.getName()+Arrays.asList(params).toString();
            }
        };
    }


    public static String generatorByIdKey(String dbName,String id){
        return dbName + "::" + RESULT_PREP + "[" + id + "]";
    }

    public static String generatorAllKey(String methodName,String dbName,Object... params){
        return dbName + "::" + methodName + Arrays.asList(params).toString();
    }

    public static String generatorParamKeyGenerator(String methodName,String dbName,Object... params){
        return dbName + "::" + methodName + Arrays.asList(params).toString();
    }

    public static String generatorCountKey(String methodName,String dbName,Object... params){
        return dbName + "::" + COUNT_PREP +methodName+ Arrays.asList(params).toString() ;
    }

}

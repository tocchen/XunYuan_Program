package top.tocchen.utils.redis;

/**
 * @author tocchen
 * @date 2023/2/21 17:52
 * @since jdk 1.8
 **/

public interface CacheKeyName {
    String QUERY_KEY = "queryKeyGenerator";

    String QUERY_ID_KEY = "queryByIdKeyGenerator";

    String REMOVE_ID_KEY = "removeByIdKeyGenerator";

    String COUNT_KEY = "queryAllCountKeyGenerator";

    String COUNT_PARAM_KEY = "queryByParamCountKeyGenerator";
}

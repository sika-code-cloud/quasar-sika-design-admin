package com.quasar.sika.design.server.common.shiro.constant;

public interface ShiroConstant {
    /**
     * 请求头 - token  【注：ShiroConfig中放行】
     */
    String REQUEST_HEADER = "token";
    /**
     * TOKEN前缀
     */
    String REDIS_PREFIX_LOGIN = "TOKEN:{}";
    String CACHE_KEY = "SHIRO:CACHE:";
    String SESSION_KEY = "SHIRO:SESSION:";
    String ID_KEY = "username";
    int EXPIRE = 1800;


    String HASH_ALGORITHM_NAME = "SHA-256";
    /**  循环次数 **/
    int HASH_ITERATIONS = 16;
}

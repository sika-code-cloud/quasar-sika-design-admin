package com.sika.code.standard.base.pojo.domain;

import com.sika.code.basic.pojo.domain.BaseDomain;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 基础领域接口
 *
 * @author daiqi
 * @create 2019-05-13 13:40
 */
public interface BaseStandardDomain extends BaseDomain {

    /**
     * <p>
     * 获取redisTemplate实例
     * </p>
     *
     * @return RedisTemplate
     * @author daiqi
     * @date 2019/6/20 22:52
     */
    default RedisTemplate redisTemplate() {
        return getBean(RedisTemplate.class);
    }

    /**
     * @param cacheKey : 缓存key
     * @param <T>
     * @return 值
     */
    default <T> T getFromCache(String cacheKey) {
        return (T) redisTemplate().opsForValue().get(cacheKey);
    }

    /**
     * 将key和值放入缓存
     *
     * @param cacheKey : 缓存key
     * @param value    : 缓存的value
     */
    default void putToCache(String cacheKey, Object value) {
        redisTemplate().opsForValue().set(cacheKey, value, 600L, TimeUnit.SECONDS);
    }

    /**
     * 将key和值放入缓存
     *
     * @param cacheKey : 缓存key
     * @param value    : 缓存的value
     * @param timeout  : 超时时间
     * @param timeUnit : 超时单位
     */
    default void putToCache(String cacheKey, Object value, Long timeout, TimeUnit timeUnit) {
        redisTemplate().opsForValue().set(cacheKey, value, timeout, timeUnit);
    }

    /**
     * 将缓存key移除缓存
     *
     * @param cacheKey : 缓存的key
     * @return 成功返回true
     */
    default Boolean removeToCache(String cacheKey) {
        return redisTemplate().delete(cacheKey);
    }

}

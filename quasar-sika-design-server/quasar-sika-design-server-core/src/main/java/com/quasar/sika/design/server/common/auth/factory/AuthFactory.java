package com.quasar.sika.design.server.common.auth.factory;

import cn.hutool.core.lang.SimpleCache;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.quasar.sika.design.server.common.auth.cache.AuthStateRedisCache;
import com.quasar.sika.design.server.common.auth.properties.AuthConfigExp;
import com.quasar.sika.design.server.common.auth.properties.AuthRequestProperties;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.spring.SpringUtil;
import com.sika.code.exception.BusinessException;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;

/**
 * 认证工厂类
 *
 * @author daiqi
 * @create 2020-12-30 23:26
 */
public class AuthFactory {
    private static Logger log = LoggerFactory.getLogger(AuthFactory.class);
    /**
     * 认证请求对象缓存
     */
    private static final SimpleCache<String, AuthRequest> AUTH_REQUEST_SIMPLE_CACHE = new SimpleCache<>();

    /**
     * 获取保存token的缓存对象
     */
    public static AuthStateRedisCache getAuthStateCache() {
        return SpringUtil.getBean(AuthStateRedisCache.class);
    }

    public static AuthRequestProperties getAuthRequestProperties() {
        return SpringUtil.getBean(AuthRequestProperties.class);
    }

    /**
     * 根据具体的授权来源，获取授权请求工具类
     *
     * @param source
     * @return
     */
    public static AuthRequest getAuthRequest(String source) {
        AuthRequest authRequest = AUTH_REQUEST_SIMPLE_CACHE.get(source);
        if (ObjectUtil.isNotNull(authRequest)) {
            return authRequest;
        }
        AuthConfigExp configExp = getAuthRequestProperties().getOauthConfig().get(source);
        if (BaseUtil.isNull(configExp)) {
            throw new BusinessException(StrUtil.format("source【{}】对应的授权配置不存在，请检查", source));
        }
        String requestClassName = configExp.getRequestClassName();
        if (StrUtil.isBlank(requestClassName)) {
            throw new BusinessException(StrUtil.format("source【{}】对应的请求配置类为空，请检查", source));
        }
        Class<?> requestClass;
        try {
            requestClass = Class.forName(requestClassName);
            Constructor<?> defaultRequest = ReflectUtil.getConstructor(requestClass, AuthConfig.class, AuthStateCache.class);
            authRequest = (AuthRequest) defaultRequest.newInstance(configExp, getAuthStateCache());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException(StrUtil.format("source【{}】对应的请求配置类【{}】有误，请检查", source, requestClassName));
        }
        if (ObjectUtil.isNull(authRequest)) {
            throw new BusinessException(StrUtil.format("source【{}】对应的请求配置类不存在【{}】有误，请检查", source, requestClassName));
        }
        return AUTH_REQUEST_SIMPLE_CACHE.put(source, authRequest);
    }

}

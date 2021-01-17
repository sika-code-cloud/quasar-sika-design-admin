package com.quasar.sika.design.server.common.shiro.config;

import cn.hutool.core.codec.Base64;
import com.quasar.sika.design.server.common.shiro.constant.ShiroConstant;
import com.quasar.sika.design.server.common.shiro.filter.PermissionsAuthFilter;
import com.quasar.sika.design.server.common.shiro.filter.RolesAuthFilter;
import com.quasar.sika.design.server.common.shiro.filter.TokenCheckFilter;
import com.quasar.sika.design.server.common.shiro.filter.UserCheckFilter;
import com.quasar.sika.design.server.common.shiro.realm.ShiroRealm;
import com.quasar.sika.design.server.common.shiro.service.ShiroService;
import com.quasar.sika.design.server.common.shiro.util.ShiroSessionIdGenerator;
import com.quasar.sika.design.server.common.shiro.manager.ShiroSessionManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p> Shiro配置类 </p>
 *
 * @author : sikadai
 * @description :
 * @date : 2020/12/26 15:52
 */
@Configuration
public class ShiroConfig {
    @Autowired
    private RedisProperties redisProperties;
    @Autowired
    private ShiroService shiroService;

    /**
     * 开启Shiro-aop注解支持：使用代理方式所以需要开启代码支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * Shiro基础配置
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactory(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 自定义过滤器
        Map<String, Filter> filtersMap = new LinkedHashMap<>();
        // 定义过滤器名称 【注：map里面key值对于的value要为authc才能使用自定义的过滤器】
        filtersMap.put("scPerms", new PermissionsAuthFilter());
        filtersMap.put("scRoles", new RolesAuthFilter());
        filtersMap.put("authc", new TokenCheckFilter());
        filtersMap.put("user", new UserCheckFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);

        // 登录的路径: 如果你没有登录则会跳到这个页面中 - 如果没有设置值则会默认跳转到工程根目录下的"/login.jsp"页面 或 "/login" 映射
        shiroFilterFactoryBean.setLoginUrl("/auth/unLogin/anon");
        // 登录成功后跳转的主页面 （这里没用，前端vue控制了跳转）
//        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 设置没有权限时跳转的url
        shiroFilterFactoryBean.setUnauthorizedUrl("/auth/unAuth/anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroService.loadFilterChainDefinitionMap());
        return shiroFilterFactoryBean;
    }

    /**
     * cookie对象;
     * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }


    /**
     * cookie管理对象;
     * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }

    /**
     * 安全管理器
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 自定义session管理
        securityManager.setSessionManager(sessionManager());
        // 自定义Cache实现缓存管理
        securityManager.setCacheManager(cacheManager());
        // 自定义Realm验证
        securityManager.setRealm(shiroRealm());
        // 设置remember
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * 身份验证器
     */
    @Bean
    public ShiroRealm shiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealm;
    }

    /**
     * 自定义Realm的加密规则 -> 凭证匹配器：将密码校验交给Shiro的SimpleAuthenticationInfo进行处理,在这里做匹配配置
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用SHA256算法;
        shaCredentialsMatcher.setHashAlgorithmName(ShiroConstant.HASH_ALGORITHM_NAME);
        shaCredentialsMatcher.setHashIterations(ShiroConstant.HASH_ITERATIONS);
        return shaCredentialsMatcher;
    }

    /**
     * Redis集群使用RedisClusterManager，单个Redis使用RedisManager
     * @param redisProperties
     */
//    @Bean
//    public RedisClusterManager redisManager(RedisProperties redisProperties) {
//        RedisClusterManager redisManager = new RedisClusterManager();
//        redisManager.setHost(redisProperties.getHost());
//        redisManager.setPassword(redisProperties.getPassword());
//        return redisManager;
//    }

    /**
     * 配置Redis管理器：使用的是shiro-redis开源插件
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisProperties.getHost() + ":" + redisProperties.getPort());
        redisManager.setTimeout((int) (redisProperties.getTimeout().getSeconds() / 1000));
        redisManager.setPassword(redisProperties.getPassword());
        return redisManager;
    }

    /**
     * 配置Cache管理器：用于往Redis存储权限和角色标识  (使用的是shiro-redis开源插件)
     */
    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        redisCacheManager.setKeyPrefix(ShiroConstant.CACHE_KEY);
        // 配置缓存的话要求放在session里面的实体类必须有个id标识 注：这里id为用户表中的主键，否-> 报：User must has getter for field: xx
        redisCacheManager.setPrincipalIdFieldName(ShiroConstant.ID_KEY);
        return redisCacheManager;
    }

    /**
     * SessionID生成器
     */
    @Bean
    public ShiroSessionIdGenerator sessionIdGenerator() {
        return new ShiroSessionIdGenerator();
    }

    /**
     * 配置RedisSessionDAO (使用的是shiro-redis开源插件)
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setSessionIdGenerator(sessionIdGenerator());
        redisSessionDAO.setKeyPrefix(ShiroConstant.SESSION_KEY);
        redisSessionDAO.setExpire(ShiroConstant.EXPIRE);
        return redisSessionDAO;
    }

    /**
     * 配置Session管理器
     */
    @Bean
    public SessionManager sessionManager() {
        ShiroSessionManager shiroSessionManager = new ShiroSessionManager();
        shiroSessionManager.setSessionDAO(redisSessionDAO());
        return shiroSessionManager;
    }

}

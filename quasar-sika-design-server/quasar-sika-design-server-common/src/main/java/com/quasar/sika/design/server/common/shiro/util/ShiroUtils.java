package com.quasar.sika.design.server.common.shiro.util;

import com.quasar.sika.design.server.business.user.pojo.dto.UserDTO;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.spring.SpringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisSessionDAO;

import java.util.Collection;
import java.util.Objects;

public class ShiroUtils {

    /**
     * 私有构造器
     **/
    private ShiroUtils() {
    }

    /**
     * 获取当前用户Session
     *
     * @Return SysUserDTO 用户信息
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static String getSessionId() {
        return getSession().getId().toString();
    }

    private static RedisSessionDAO getRedisSessionDAO() {
        return SpringUtil.getBean(RedisSessionDAO.class);
    }

    /**
     * 用户登出
     */
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * 获取当前用户信息
     *
     * @Return SysUserDTO 用户信息
     */
    public static UserDTO getUserInfo() {
        return (UserDTO) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 删除用户缓存信息
     *
     * @Param username  用户名称
     * @Param isRemoveSession 是否删除Session，删除后用户需重新登录
     */
    public static void deleteCache(String username, boolean isRemoveSession) {
        if (!isRemoveSession) {
            return;
        }
        //从缓存中获取Session
        Session session = null;
        // 获取当前已登录的用户session列表
        Collection<Session> sessions = getRedisSessionDAO().getActiveSessions();
        UserDTO sysUserDTO;
        Object attribute = null;
        // 遍历Session,找到该用户名称对应的Session
        for (Session sessionInfo : sessions) {
            attribute = sessionInfo.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (attribute == null) {
                continue;
            }
            sysUserDTO = (UserDTO) ((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
            if (sysUserDTO == null) {
                continue;
            }
            if (Objects.equals(sysUserDTO.getUsername(), username)) {
                session = sessionInfo;
                // 清除该用户以前登录时保存的session，强制退出  -> 单用户登录处理
                if (isRemoveSession) {
                    getRedisSessionDAO().delete(session);
                }
            }
        }

        if (BaseUtil.isAnyNull(session, attribute)) {
            return;
        }
        //删除session
        if (isRemoveSession) {
            getRedisSessionDAO().delete(session);
        }
        //删除Cache，再访问受限接口时会重新授权
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        Authenticator authc = securityManager.getAuthenticator();
        ((LogoutAware) authc).onLogout((SimplePrincipalCollection) attribute);
    }

    public static <T> T setAttributeToSession(String name, T value) {
        getSession().setAttribute(name, value);
        return value;
    }

    public static <T> T getAttributeFromSession(String name) {
        return (T) getSession().getAttribute(name);
    }

    public static Object removeAttributeFromSession(String name) {
        return getSession().removeAttribute(name);
    }

    /**
     * 从缓存中获取指定用户名的Session
     *
     * @param username
     */
    private static Session getSessionByUsername(String username) {
        // 获取当前已登录的用户session列表
        Collection<Session> sessions = getRedisSessionDAO().getActiveSessions();
        UserDTO user;
        Object attribute;
        // 遍历Session,找到该用户名称对应的Session
        for (Session session : sessions) {
            attribute = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (attribute == null) {
                continue;
            }
            user = (UserDTO) ((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
            if (user == null) {
                continue;
            }
            if (Objects.equals(user.getUsername(), username)) {
                return session;
            }
        }
        return null;
    }

}

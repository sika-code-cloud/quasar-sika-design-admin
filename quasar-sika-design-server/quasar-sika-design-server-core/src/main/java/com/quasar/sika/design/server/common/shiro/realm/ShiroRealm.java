package com.quasar.sika.design.server.common.shiro.realm;

import com.quasar.sika.design.server.business.menu.pojo.dto.MenuDTO;
import com.quasar.sika.design.server.business.menu.service.MenuService;
import com.quasar.sika.design.server.business.role.pojo.dto.RoleDTO;
import com.quasar.sika.design.server.business.role.service.RoleService;
import com.quasar.sika.design.server.business.user.entity.UserEntity;
import com.quasar.sika.design.server.business.user.pojo.dto.UserDTO;
import com.quasar.sika.design.server.business.user.service.UserService;
import com.quasar.sika.design.server.common.auth.token.OauthLoginToken;
import com.quasar.sika.design.server.common.shiro.util.SHA256Util;
import com.quasar.sika.design.server.common.shiro.util.ShiroUtils;
import com.sika.code.basic.constant.BaseConstant;
import com.sika.code.basic.util.BaseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    @Override
    public String getName() {
        return "shiroRealm";
    }

    /**
     * 赋予角色和权限:用户进行权限验证时 Shiro会去缓存中找,如果查不到数据,会执行这个方法去查权限,并放入缓存中
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 获取用户
        UserEntity user = (UserEntity) principalCollection.getPrimaryPrincipal();
        Long userId = user.getId();
        // 这里可以进行授权和处理
        Set<String> rolesSet = new HashSet<>();
        Set<String> permsSet = new HashSet<>();
        // 获取当前用户对应的权限(这里根据业务自行查询)
        List<RoleDTO> roleList = roleService.listByUserId(userId);
        for (RoleDTO role : roleList) {
            rolesSet.add(role.getRoleKey());
            List<MenuDTO> menuList = menuService.listByRoleId(role.getId());
            for (MenuDTO menu : menuList) {
                permsSet.add(menu.getPerms());
            }
        }
        //将查到的权限和角色分别传入authorizationInfo中
        authorizationInfo.setStringPermissions(permsSet);
        authorizationInfo.setRoles(rolesSet);
        log.info("--------------- 赋予角色和权限成功！ ---------------");
        return authorizationInfo;
    }

    /**
     * 身份认证 - 之后走上面的 授权
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken instanceof OauthLoginToken) {
            return oauthLogin((OauthLoginToken) authenticationToken);
        } else {
            return usernamePasswordLogin((UsernamePasswordToken) authenticationToken);
        }
    }

    private SimpleAuthenticationInfo oauthLogin(OauthLoginToken oauthLoginToken) {
        String password256 = SHA256Util.sha256(oauthLoginToken.getUsername(), oauthLoginToken.getUsername());
        UserDTO userDTO = new UserDTO()
                .setUsername(oauthLoginToken.getUsername())
                .build(oauthLoginToken.getAuthUser());
        return new SimpleAuthenticationInfo(userDTO, password256, ByteSource.Util.bytes(oauthLoginToken.getUsername()), getName());
    }

    private SimpleAuthenticationInfo usernamePasswordLogin(UsernamePasswordToken tokenInfo) {
        // 获取用户输入的账号
        String username = tokenInfo.getUsername();
        // 获取用户输入的密码
        String password = String.valueOf(tokenInfo.getPassword());

        // 通过username从数据库中查找 User对象，如果找到进行验证
        // 实际项目中,这里可以根据实际情况做缓存,如果不做,Shiro自己也是有时间间隔机制,2分钟内不会重复执行该方法
        UserDTO user = userService.findByUsername(username);
        // 判断账号是否存在
        if (BaseUtil.isNull(user)) {
            //返回null -> shiro就会知道这是用户不存在的异常
            return null;
        }
        // 验证密码 【注：这里不采用shiro自身密码验证 ， 采用的话会导致用户登录密码错误时，已登录的账号也会自动下线！  如果采用，移除下面的清除缓存到登录处 处理】
        if (BaseUtil.notEquals(SHA256Util.sha256(password, username), user.getPassword())) {
            throw new IncorrectCredentialsException("用户名或者密码错误");
        }
        // 判断账号是否被冻结
        if (user.getAvailable() == null || BaseConstant.AvailableEnum.notAvailable(user.getAvailable())) {
            throw new LockedAccountException("当前账号已被冻结");
        }
        /**
         * 进行验证 -> 注：shiro会自动验证密码
         * 参数1：principal -> 放对象就可以在页面任意地方拿到该对象里面的值
         * 参数2：hashedCredentials -> 密码
         * 参数3：credentialsSalt -> 设置盐值
         * 参数4：realmName -> 自定义的Realm
         */
        user.setToken(ShiroUtils.getSessionId());
        // 验证成功开始踢人(清除缓存和Session)
        ShiroUtils.deleteCache(username, true);
        // 认证成功后更新token
        updateToken(user);
        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getUsername()), getName());
    }

    private void updateToken(UserDTO userFromDb) {
        // 认证成功后更新token
        UserDTO userForUpdate = new UserDTO();
        userForUpdate.setId(userFromDb.getId());
        userForUpdate.setToken(userFromDb.getToken());
        userService.updateById(userForUpdate);
    }
}
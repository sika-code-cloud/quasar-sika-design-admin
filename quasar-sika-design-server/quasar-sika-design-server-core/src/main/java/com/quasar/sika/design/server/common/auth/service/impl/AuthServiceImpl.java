package com.quasar.sika.design.server.common.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.quasar.sika.design.server.business.thirdoauthuser.service.ThirdOauthUserService;
import com.quasar.sika.design.server.business.user.pojo.dto.UserDTO;
import com.quasar.sika.design.server.business.user.service.UserService;
import com.quasar.sika.design.server.common.auth.factory.AuthFactory;
import com.quasar.sika.design.server.common.auth.pojo.request.*;
import com.quasar.sika.design.server.common.auth.pojo.response.AuthResponse;
import com.quasar.sika.design.server.common.auth.pojo.response.OauthResponse;
import com.quasar.sika.design.server.common.auth.service.AuthService;
import com.quasar.sika.design.server.common.shiro.util.SHA256Util;
import com.quasar.sika.design.server.common.shiro.util.ShiroUtils;
import com.sika.code.basic.util.Assert;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private ThirdOauthUserService thirdOauthUserService;

    @Override
    public AuthResponse register(AuthRegisterRequest request) {
        request.setPassword(SHA256Util.sha256(request));
        boolean saveSuccess = userService.save(request);
        if (BooleanUtil.isFalse(saveSuccess)) {
            throw new BusinessException("保存失败,请校验保存参数");
        }
        return AuthResponse.success(request);
    }

    @Override
    public AuthResponse login(AuthLoginRequest request) {
        // 验证身份和登陆
        UsernamePasswordToken token = request.build().getToken();
        // 账号登录
        if (StrUtil.isBlank(request.getUsername()) || StrUtil.isBlank(request.getPassword())) {
            throw new BusinessException("用户名或者密码为空");
        }
        // 拿到当前用户(可能还是游客，没有登录)
        Subject currentUser = SecurityUtils.getSubject();
        // 如果用户已经登录,进行登录功能
        if (BooleanUtil.isTrue(currentUser.isAuthenticated())) {
            return AuthResponse.success(ShiroUtils.getUserInfo());
        }
        try {
            currentUser.login(token);
        } catch (UnknownAccountException e) {
            throw new BusinessException("账号不存在");
        } catch (IncorrectCredentialsException e) {
            throw new BusinessException("用户名或者密码错误");
        } catch (LockedAccountException e) {
            throw new BusinessException("登录失败，该用户已被冻结!");
        } catch (AuthenticationException e) {
            throw new BusinessException("系统错误");
        }
        return AuthResponse.success(ShiroUtils.getUserInfo());
    }

    @Override
    public AuthResponse updatePassword(AuthUpdatePasswordRequest request) {
        UserDTO currentUser = ShiroUtils.getUserInfo();
        if (BaseUtil.isNull(currentUser)) {
            throw new BusinessException("当前用户信息异常");
        }
        UserDTO userFromDB = userService.findByPrimaryKey(request.getUserId());
        if (BaseUtil.isNull(userFromDB)) {
            throw new BusinessException("当前用户数据不存在");
        }
        updatePasswordCore(userFromDB.getUsername(), request.getPassword());
        return AuthResponse.success();
    }

    @Override
    public AuthResponse updateCurrentPassword(AuthUpdatePasswordRequest request) {
        UserDTO currentUser = ShiroUtils.getUserInfo();
        Assert.verifyObjNullMsg(currentUser, "当前用户信息异常");
        String username = currentUser.getUsername();
        String password = request.getPassword();
        updatePasswordCore(username, password);
        return AuthResponse.success();
    }

    @Override
    public String getAuthorizeUrl(String source) {
        log.info("获取授权URL:getAuthorizeUrl：" + source);
        AuthRequest authRequest = AuthFactory.getAuthRequest(source);
        return authRequest.authorize(AuthStateUtils.createState());
    }

    @Override
    public OauthResponse oauthLogin(String source, AuthCallback callback) {
        log.info("开始oauthLogin：" + source + " 请求 params：" + JSONObject.toJSONString(callback));
        AuthRequest authRequest = AuthFactory.getAuthRequest(source);
        me.zhyd.oauth.model.AuthResponse<AuthUser> response = authRequest.login(callback);
        log.info("授权登录响应参数：{}", JSONObject.toJSONString(response));
        if (response.ok()) {
            AuthUser authUser = response.getData();
            // 执行登录
            AuthLoginRequest authLoginRequest = new OauthLoginRequest().build(authUser);
            AuthResponse authResponse = login(authLoginRequest);
            // 保存或者授权登录数据
            thirdOauthUserService.modifyByAuthUser(authUser);
            return BeanUtil.toBean(authResponse, OauthResponse.class).setAuthUser(authUser);
        }
        throw new BusinessException(response.getMsg());
    }

    @Override
    public OauthResponse bindOauthUser(BindOauthUserRequest request) {
        return null;
    }

    /**
     * 更新密码的核心方法
     * 根据username查询用户信息-然后更新密码
     *
     * @param username : 用户名
     * @param password : 密码
     */
    private void updatePasswordCore(String username, String password) {
        UserDTO userDTOFromDb = userService.findByUsername(username);
        if (BaseUtil.isNull(userDTOFromDb)) {
            throw new BusinessException("当前用户不存在");
        }

        UserDTO userForUpdate = new UserDTO();
        userForUpdate.setId(userDTOFromDb.getId());
        userForUpdate.setPassword(SHA256Util.sha256(password, username));
        boolean updateFlag = userService.updateById(userForUpdate);
        if (BooleanUtil.isFalse(updateFlag)) {
            throw new BusinessException("密码修改失败,请稍后重试");
        }
    }

}

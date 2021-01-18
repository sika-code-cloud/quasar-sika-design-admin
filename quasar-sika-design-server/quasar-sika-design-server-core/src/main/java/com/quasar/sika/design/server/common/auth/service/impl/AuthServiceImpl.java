package com.quasar.sika.design.server.common.auth.service.impl;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.quasar.sika.design.server.business.thirdoauthuser.pojo.dto.ThirdOauthUserDTO;
import com.quasar.sika.design.server.business.thirdoauthuser.service.ThirdOauthUserService;
import com.quasar.sika.design.server.business.user.pojo.dto.UserDTO;
import com.quasar.sika.design.server.business.user.service.UserService;
import com.quasar.sika.design.server.common.auth.constant.AuthErrorCodeEnum;
import com.quasar.sika.design.server.common.auth.factory.AuthFactory;
import com.quasar.sika.design.server.common.auth.pojo.dto.OauthStateCacheDTO;
import com.quasar.sika.design.server.common.auth.pojo.request.*;
import com.quasar.sika.design.server.common.auth.pojo.response.AuthResponse;
import com.quasar.sika.design.server.common.auth.pojo.response.OauthResponse;
import com.quasar.sika.design.server.common.auth.service.AuthService;
import com.quasar.sika.design.server.common.shiro.util.SHA256Util;
import com.quasar.sika.design.server.common.shiro.util.ShiroUtils;
import com.sika.code.basic.util.Assert;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.exception.BusinessException;
import com.sika.code.standard.base.pojo.domain.BaseStandardDomain;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService, BaseStandardDomain {
    @Autowired
    private UserService userService;
    @Autowired
    private ThirdOauthUserService thirdOauthUserService;

    private static final String OAUTH_STATE_KEY = "oauth:state:";
    private static final String OAUTH_USER_KEY = "oauth:user:";

    @Override
    public AuthResponse currentUser() {
        UserDTO user = ShiroUtils.getUserInfo();
        UserDTO userFromDb = userService.findByPrimaryKey(user.getId());
        return AuthResponse.success(userFromDb).build();
    }

    @Override
    public boolean checkForgetPasswordEmail(AuthForgetPasswordRequest request) {
        Validator.validateEmail(request.getEmail(), "邮箱格式有误");
        // 校验邮箱是否存在
        Assert.verifyDataNotExistentMsg(userService.findByEmail(request.getEmail()), "当前邮箱不存在");
        return true;
    }

    @Override
    public boolean checkRegisterEmail(AuthRegisterRequest registerRequest) {
        Validator.validateEmail(registerRequest.getEmail(), "邮箱格式有误");
        // 校验邮箱是否存在
        Assert.verifyDataExistentMsg(userService.findByEmail(registerRequest.getEmail()), "当前邮箱已经被注册");
        return true;
    }

    @Override
    public boolean checkRegisterPhone(AuthRegisterRequest registerRequest) {
        Validator.validateMobile(registerRequest.getPhone(), "手机格式有误");
        // 校验手机是否存在
        Assert.verifyDataExistentMsg(userService.findByPhone(registerRequest.getPhone()), "当前手机号已经被注册");
        return true;
    }

    @Override
    public boolean checkRegisterUsername(AuthRegisterRequest registerRequest) {
        Assert.verifyStrEmpty(registerRequest.getUsername(), "用户名");
        // 校验手机是否存在
        Assert.verifyDataExistentMsg(userService.findByUsername(registerRequest.getUsername()), "当前用户名已经被注册");
        return true;
    }

    @Override
    public AuthResponse loginPhone(AuthLoginPhoneRequest request) {
        Assert.verifyStrEmpty(request.getPhone(), "手机号");
        // 根据手机查询用户数据
        UserDTO userDTO = userService.findByPhone(request.getPhone());
        Assert.verifyDataNotExistent(userDTO, "账号不存在");
        // 设置用户名
        request.setUsername(userDTO.getUsername());
        return login(request);
    }

    public AuthResponse bindOauth(AuthLoginRequest request) {

        return login(request);
    }

    @Override
    public AuthResponse login(AuthLoginRequest request) {
        // 账号登录
        if (StrUtil.isBlank(request.getUsername()) || ArrayUtil.isEmpty(request.getPassword())) {
            throw new BusinessException("用户名或者密码为空");
        }
        // 拿到当前用户(可能还是游客，没有登录)
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(request);
        } catch (UnknownAccountException e) {
            throw new BusinessException("账号不存在");
        } catch (IncorrectCredentialsException e) {
            throw new BusinessException("登录名或者密码错误");
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
    public String getAuthorizeUrl(String source, String clientUrl) {
        log.info("获取授权URL:getAuthorizeUrl：" + source);
        AuthRequest authRequest = AuthFactory.getAuthRequest(source);
        String state = AuthStateUtils.createState();
        String thirdUrl = authRequest.authorize(state);
        setOauthStateCache(source, state, clientUrl);
        log.info("thirdUrl:{}, authRequest:{}", thirdUrl, JSONObject.toJSONString(authRequest));
        return thirdUrl;
    }

    private void setOauthStateCache(String source, String state, String clientUrl) {
        OauthStateCacheDTO cacheDTO = new OauthStateCacheDTO()
                .setClientUrl(clientUrl)
                .setClientSessionId(ShiroUtils.getSessionId());
        log.info("缓存的数据为：{}", JSONObject.toJSONString(cacheDTO));
        putToCache(buildStateCacheKey(source, state), cacheDTO);
    }

    @Override
    public OauthStateCacheDTO getOauthStateCache(String source, String state) {
        return getFromCache(buildStateCacheKey(source, state));
    }

    @Override
    public OauthResponse oauthLogin(String source, AuthCallback callback) {
        log.info("开始oauthLogin：" + source + " 请求 params：" + JSONObject.toJSONString(callback));
        AuthRequest authRequest = AuthFactory.getAuthRequest(source);
        me.zhyd.oauth.model.AuthResponse<AuthUser> response = authRequest.login(callback);
        log.info("授权登录响应参数：{}", JSONObject.toJSONString(response));
        if (response.ok()) {
            AuthUser authUser = response.getData();
            // 保存或者授权登录数据
            thirdOauthUserService.modifyByAuthUser(authUser, callback.getState());
            OauthStateCacheDTO cacheDTO = getOauthStateCache(source, callback.getState());
            return new OauthResponse()
                    .setSource(source)
                    .setClientUrl(cacheDTO.getClientUrl())
                    .setOauthToken(callback.getState());
        }
        throw new BusinessException(response.getMsg());
    }

    @Override
    public AuthResponse doOauthLogin(AuthOauthLoginRequest request) {
        log.info("开始doOauthLogin：" + request.getSource() + " 请求 params：" + JSONObject.toJSONString(request));
        ThirdOauthUserDTO oauthUserDTO = thirdOauthUserService.findByStateAndSource(request.getOauthToken(), request.getSource());
        return oauthLoginCore(oauthUserDTO);
    }

    private String buildStateCacheKey(String source, String state) {
        return OAUTH_STATE_KEY + source.toLowerCase() + ":" + state;
    }

    @Override
    public AuthResponse bindOauthUser(AuthLoginRequest request) {
        ThirdOauthUserDTO oauthUser = ShiroUtils.getAttributeFromSession(OAUTH_USER_KEY);
        Assert.verifyObjNullMsg(oauthUser, "授权信息已经失效，请返回登录重新授权");
        // 执行登录
        login(request);
        // 没有异常-登录成功 执行核心授权登录逻辑
        AuthResponse authResponse = oauthLoginCore(oauthUser);
        if (authResponse.getSuccess()) {
            // 移除缓存
            ShiroUtils.removeAttributeFromSession(OAUTH_USER_KEY);
        }
        return authResponse;
    }

    /**
     * 授权登录核心逻辑
     * 1. 已经登录 更新授权用户绑定的用户信息
     * 2. 没有登录-已经绑定 根据绑定的userId查询用户信息，执行登录
     * 3. 没有登录-没有绑定 返回错误码-授权用户没有绑定-前端跳转至绑定页面
     * @param oauthUserDTO
     * @return
     */
    private AuthResponse oauthLoginCore(ThirdOauthUserDTO oauthUserDTO) {
        Assert.verifyObjNullMsg(oauthUserDTO, "授权用户信息为空");
        Assert.verifyObjNullMsg(oauthUserDTO.getId(), "授权用户信息异常");
        Subject subject = SecurityUtils.getSubject();
        // 已经登录
        if (subject.isAuthenticated()) {
            UserDTO user = ShiroUtils.getUserInfo();
            ThirdOauthUserDTO oauthUserForUpdate = new ThirdOauthUserDTO();
            oauthUserForUpdate.setId(oauthUserDTO.getId());
            oauthUserForUpdate.setUserId(user.getId());
            thirdOauthUserService.updateById(oauthUserForUpdate);
            return AuthResponse.success(user);
        }
        UserDTO userFromDb = userService.findByPrimaryKey(oauthUserDTO.getUserId());
        if (BaseUtil.isNotNull(userFromDb)) {
            AuthLoginRequest authLoginRequest = new AuthLoginRequest(userFromDb.getUsername(), userFromDb.getPassword());
            authLoginRequest.setEncryptedPassword(true);
            return login(authLoginRequest);
        }
        // 如果既没有登录又没有绑定 --- 需要缓存授权信息至会话中
        ShiroUtils.setAttributeToSession(OAUTH_USER_KEY, oauthUserDTO);
        return AuthResponse.error(AuthErrorCodeEnum.OAUTH_USER_NOT_AUTH).build();
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

package com.quasar.sika.design.server.common.auth.service.impl;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import com.quasar.sika.design.server.business.user.pojo.dto.UserDTO;
import com.quasar.sika.design.server.business.user.service.UserService;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthUpdatePasswordRequest;
import com.quasar.sika.design.server.common.auth.service.AuthService;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthLoginRequest;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthRegisterRequest;
import com.quasar.sika.design.server.common.auth.pojo.response.AuthResponse;
import com.quasar.sika.design.server.common.shiro.util.SHA256Util;
import com.quasar.sika.design.server.common.shiro.util.ShiroUtils;
import com.sika.code.basic.errorcode.BaseErrorCodeEnum;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.exception.BusinessException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserService userService;

    @Override
    public AuthResponse register(AuthRegisterRequest request) {
        request.setPassword(SHA256Util.sha256(request));
        boolean saveSuccess = userService.save(request);
        if (BooleanUtil.isFalse(saveSuccess)) {
            throw new BusinessException("保存失败,请校验保存参数");
        }
        return  AuthResponse.success(request);
    }

    @Override
    public AuthResponse login(AuthLoginRequest request) {
        // 账号登录
        if (StrUtil.isBlank(request.getUsername()) || StrUtil.isBlank(request.getPassword())) {
            throw new BusinessException(BaseErrorCodeEnum.PARAM_ERROR, "参数不全");
        }
        // 拿到当前用户(可能还是游客，没有登录)
        Subject currentUser = SecurityUtils.getSubject();
        // 如果这个用户没有登录,进行登录功能
        if (BooleanUtil.isFalse(currentUser.isAuthenticated())) {
            try {
                // 验证身份和登陆
                UsernamePasswordToken token = new UsernamePasswordToken(request.getUsername(), request.getPassword());
                // String token = MD5Utils.encrypt( String.valueOf( System.currentTimeMillis() ) );
                currentUser.login(token);
            } catch (UnknownAccountException e) {
                throw new BusinessException(BaseErrorCodeEnum.PARAM_ERROR, "账号不存在");
            } catch (IncorrectCredentialsException e) {
                throw new BusinessException(BaseErrorCodeEnum.BUSINESS_EXCEPTION, "用户名或者密码错误");
            } catch (LockedAccountException e) {
                throw new BusinessException(BaseErrorCodeEnum.BUSINESS_EXCEPTION, "登录失败，该用户已被冻结!");
            } catch (AuthenticationException e) {
                throw new BusinessException(BaseErrorCodeEnum.SYS_ERROR, "系统错误");
            }
        }
        return AuthResponse.success();
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
        if (BaseUtil.isNull(currentUser)) {
            throw new BusinessException("当前用户信息异常");
        }
        String username = currentUser.getUsername();
        String password = request.getPassword();
        updatePasswordCore(username, password);
        return AuthResponse.success();
    }

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

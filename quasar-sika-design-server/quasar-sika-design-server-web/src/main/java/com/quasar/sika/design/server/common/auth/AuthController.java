package com.quasar.sika.design.server.common.auth;


import com.quasar.sika.design.server.business.user.mapper.UserMapper;
import com.quasar.sika.design.server.business.userrole.mapper.UserRoleMapper;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthLoginRequest;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthRegisterRequest;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthUpdatePasswordRequest;
import com.quasar.sika.design.server.common.auth.service.AuthService;
import com.quasar.sika.design.server.common.shiro.service.ShiroService;
import com.quasar.sika.design.server.common.shiro.util.ShiroUtils;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 认证 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:13
 */
@RestController(value = "authController")
@RequestMapping("auth")
public class AuthController extends BaseStandardController {
    @Autowired
    private AuthService authService;
    @Autowired
    private ShiroService shiroService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;

    @PostMapping("/register/anon")
    public Result login(@RequestBody AuthRegisterRequest request) {
        return super.generateResult(authService.register(request));
    }

    @PostMapping("/login")
    public Result login(@RequestBody AuthLoginRequest request) {
        return super.generateResult(authService.login(request));
    }

    @PostMapping("/update_current_password")
    public Result login(@RequestBody AuthUpdatePasswordRequest request) {
        return super.generateResult(authService.updateCurrentPassword(request));
    }

    @PostMapping("/update_password")
    public Result updatePassword(@RequestBody AuthUpdatePasswordRequest request) {
        return super.generateResult(authService.updatePassword(request));
    }

//    /**
//     * QQ 授权登录
//     *
//     * @param openID
//     * @param accessToken
//     * @return
//     */
//    public UserQueryPara qqLogin(String openID, String accessToken) throws QQConnectException {
//        UserQueryPara result = new UserQueryPara();
//        // 通过OpenID获取QQ用户登录信息对象(Oppen_ID代表着QQ用户的唯一标识)
//        UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
//        // 获取用户信息对象(只获取nickename、Gender、头像)
//        UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
//        if (userInfoBean.getRet() == 0) {
//            String avatar = userInfoBean.getAvatar().getAvatarURL100();
//            // 判断qq授权登录的用户是否拥有账号，如果没有则自动注册一个
//            User userInfo = userMapper.getUserInfoByQQ(openID);
//            if (userInfo == null) {
//                User user = new User();
//                // 默认将QQ注册的用户账号设置为openID TODO 后期改为QQ号
//                user.setUsername(openID);
//                user.setQqOppenId(openID);
//                user.setPwd("123456");
//                user.setPassword(SHA256Util.sha256(user.getPwd(), Constants.SALT));
//                user.setNickName(StringUtils.isBlank(userInfoBean.getNickname()) ? "无名氏" : userInfoBean.getNickname());
//                user.setAvatar(avatar);
//                user.setSex(StringUtils.isBlank(userInfoBean.getGender()) ? "0" : ("男".equals(userInfoBean.getGender()) ? "0" : "1"));
//                user.setSalt(Constants.SALT);
//                user.setFlag("1");
//                userMapper.insert(user);
//
//                // 分配权限
//                UserRole userRole = new UserRole();
//                userRole.setRoleId(2);
//                userRole.setUserId(user.getId());
//                userRoleMapper.insert(userRole);
//
//                result.setUsername(user.getUsername());
//                result.setPassword(user.getPwd());
//            } else {
//                userInfo.setAvatar(avatar);
//                userMapper.updateById(userInfo);
//
//                result.setUsername(userInfo.getUsername());
//                result.setPassword(userInfo.getPwd());
//            }
//        }
//        return result;
//    }

    @PostMapping("/logout")
    public Result logout(HttpServletRequest request, HttpServletResponse response) {
        // 更新token
//        User user = ShiroUtils.getUserInfo();
//        User userNew = userMapper.selectUserByUsername( user.getUsername() );
//        userNew.setToken( MD5Utils.encrypt( String.valueOf( System.currentTimeMillis() ) ) );
//        userMapper.updateById( userNew );
        // 用户登出
        ShiroUtils.logout();
        return generateResult("退出系统成功");
    }

    /**
     * 未登录
     */
    @RequestMapping("/unLogin")
    public Result unLogin() {
        return resultGenerator.generateResultError("未登录");
    }

    /**
     * 未授权
     */
    @RequestMapping("/unauth")
    public Result unauth() {
        return resultGenerator.generateResultError("未授权");
    }

    /**
     * token过期
     */
    @RequestMapping("/tokenExpired")
    public Result tokenExpired() {
        return resultGenerator.generateResultError("token过期，请重新登录");
    }

    /**
     * 被挤下线
     */
    @RequestMapping("/downline")
    public Result downline() {
        return resultGenerator.generateResultError("您的账号已在其他地方登录，被挤下线，请重新登录！");
    }

}

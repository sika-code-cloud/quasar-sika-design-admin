package com.quasar.sika.design.server.common.mail.bo.request.checker;

import com.quasar.sika.design.server.business.thirdoauthuser.pojo.dto.ThirdOauthUserDTO;
import com.quasar.sika.design.server.business.user.pojo.dto.UserDTO;
import com.quasar.sika.design.server.common.mail.bo.reqsponse.CheckMailCodeResponseBO;
import com.quasar.sika.design.server.common.mail.constant.MailCodeEnum;
import com.quasar.sika.design.server.common.mail.pojo.request.SendMailRequest;
import com.quasar.sika.design.server.common.mail.pojo.response.CheckMailResponse;
import com.quasar.sika.design.server.common.shiro.util.ShiroUtils;
import com.sika.code.basic.util.Assert;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.exception.BusinessException;
import lombok.Data;
import lombok.experimental.Accessors;
import me.zhyd.oauth.model.AuthUser;

/**
 * @author sikadai
 * @Description: 校验绑定授权用户邮件验证码请求逻辑类
 * @date 2021/1/30:35
 */
@Data
@Accessors(chain = true)
public class CheckBindOauthUserMailCodeRequestBO extends CheckMailCodeRequestBO {

    @Override
    protected void init() {
        buildRequest(MailCodeEnum.BIND_OAUTH_USER);
    }


    @Override
    protected CheckMailCodeResponseBO doExecute() {
        // 1: 校验验证码
        SendMailRequest sendMailRequest = checkCode();
        // 2: 没有抛出异常说明校验通过、从会话中取出授权用户信息
        UserDTO userDTO = ShiroUtils.getUserInfo();
        if (BaseUtil.isNull(userDTO) || BaseUtil.isNull(userDTO.getAuthUser())) {
            throw new BusinessException("用户尚未授权登录，请先进行授权登录");
        }
        // 3: 绑定用户
        // session中授权用户
        AuthUser authUserFromCache = userDTO.getAuthUser();
        // 数据库的用户
        UserDTO userFromDb = findOrSaveUser(sendMailRequest, authUserFromCache);
        // 第三方授权用户
        bindUser(userFromDb, authUserFromCache);
        return newResponseBO(new CheckMailResponse());
    }

    private UserDTO findOrSaveUser(SendMailRequest sendMailRequest, AuthUser authUserFromCache) {
        UserDTO userFromDb = userService().findByEmail(sendMailRequest.getTo());
        // 用户信息不存用户，先创建
        if (BaseUtil.isNull(userFromDb)) {
            userFromDb = new UserDTO();
            userFromDb.setEmail(sendMailRequest.getTo());
            userFromDb.setNickname(authUserFromCache.getNickname());
            userFromDb.setAvatar(authUserFromCache.getAvatar());
            userFromDb.setSex(Integer.valueOf(authUserFromCache.getGender().getCode()));
            userFromDb = userService().saveAndRet(userFromDb);
        }
        return userFromDb;
    }

    private void bindUser(UserDTO userFromDb, AuthUser authUserFromCache) {
        ThirdOauthUserDTO thirdOauthUserFromDb = thirdOauthUserService().findByUuidAndSource(authUserFromCache.getUuid(), authUserFromCache.getSource());
        Assert.verifyDataNotExistent(thirdOauthUserFromDb, "用户授权登录数据");

        ThirdOauthUserDTO thirdOauthUserForUpdate = new ThirdOauthUserDTO();
        thirdOauthUserForUpdate.setId(thirdOauthUserFromDb.getId());
        thirdOauthUserForUpdate.setUserId(userFromDb.getId());
        thirdOauthUserService().updateById(thirdOauthUserForUpdate);
    }
}

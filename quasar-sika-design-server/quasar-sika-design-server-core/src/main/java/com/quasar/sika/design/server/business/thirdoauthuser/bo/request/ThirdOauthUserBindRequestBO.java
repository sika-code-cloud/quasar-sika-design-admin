package com.quasar.sika.design.server.business.thirdoauthuser.bo.request;

import cn.hutool.core.util.StrUtil;
import com.quasar.sika.design.server.business.thirdoauthuser.bo.response.ThirdOauthUserBindResponseBO;
import com.quasar.sika.design.server.business.thirdoauthuser.service.ThirdOauthUserService;
import com.quasar.sika.design.server.business.user.pojo.dto.UserDTO;
import com.quasar.sika.design.server.business.user.service.UserService;
import com.quasar.sika.design.server.common.auth.pojo.request.BindOauthUserRequest;
import com.quasar.sika.design.server.common.shiro.util.ShiroUtils;
import com.sika.code.basic.util.Assert;
import com.sika.code.standard.base.pojo.bo.request.BaseStandardAlterRequestBO;
import com.sika.code.standard.base.pojo.domain.BaseStandardDomain;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ThirdOauthUserBindRequestBO extends BaseStandardAlterRequestBO<ThirdOauthUserBindResponseBO> implements BaseStandardDomain {
    /**
     * 绑定的request
     */
    private BindOauthUserRequest request;

    @Override
    protected void init() {

    }

    @Override
    protected void verify() {
        Assert.verifyObjNull(request, "绑定请求对象");
        Assert.verifyStrEmpty(request.getClientOauthCode(), "绑定的授权码");
        Assert.verifyStrEmpty(request.getEmail(), "绑定的邮箱");
    }

    @Override
    protected ThirdOauthUserBindResponseBO doExecute() {
        // 校验会话中的邮件授权码

        // 根据邮箱查询用户信息
        UserDTO userFromDb = userService().findByEmail(request.getEmail());
        // 校验
        Assert.verifyDataNotExistent(userFromDb, StrUtil.format("邮箱【{}】对应的用户数据", request.getEmail()));
        // session获取user信息
        UserDTO userFromSession = ShiroUtils.getUserInfo();
        // 校验
        Assert.verifyObjNull(userFromSession, "当前用户尚未登录，登录用户");
        // 校验授权用户
        Assert.verifyObjNull(userFromSession.getAuthUser(), "用户尚未授权登录，授权用户");

        return newResponseBO(userFromDb);
    }

    @Override
    public Class<ThirdOauthUserBindResponseBO> responseClass() {
        return ThirdOauthUserBindResponseBO.class;
    }

    private UserService userService() {
        return getBean(UserService.class);
    }

    private ThirdOauthUserService thirdOauthUserService() {
        return getBean(ThirdOauthUserService.class);
    }
}

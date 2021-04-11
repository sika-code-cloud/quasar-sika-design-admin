package com.quasar.sika.design.server.business.thirdoauthuser.executor;

import com.quasar.sika.design.server.business.thirdoauthuser.context.ThirdOauthUserBindContext;
import com.quasar.sika.design.server.business.thirdoauthuser.service.ThirdOauthUserService;
import com.quasar.sika.design.server.business.user.pojo.dto.UserDTO;
import com.quasar.sika.design.server.business.user.service.UserService;
import com.quasar.sika.design.server.common.shiro.util.ShiroUtils;
import com.sika.code.basic.pojo.dto.ServiceResult;
import com.sika.code.basic.util.Assert;
import com.sika.code.standard.base.pojo.domain.BaseStandardDomain;
import com.sika.code.standard.base.pojo.executor.BaseStandardExecutor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ThirdOauthUserBindExecutor extends BaseStandardExecutor<ThirdOauthUserBindContext> implements BaseStandardDomain {

    @Override
    protected void executeBefore() {

//        // 根据邮箱查询用户信息
//        UserDTO userFromDb = userService().findByEmail(request.getEmail());
//        // 校验
//        Assert.verifyDataNotExistent(userFromDb, StrUtil.format("邮箱【{}】对应的用户数据", request.getEmail()));
        // session获取user信息
        UserDTO userFromSession = ShiroUtils.getUserInfo();
        // 校验
        Assert.verifyObjNull(userFromSession, "当前用户尚未登录，登录用户");
        // 校验授权用户
        Assert.verifyObjNull(userFromSession.getOauthUser(), "用户尚未授权登录，授权用户");
    }

    @Override
    protected ServiceResult doExecute() {
        return ServiceResult.newInstanceOfSuccess();
    }

    private UserService userService() {
        return getBean(UserService.class);
    }

    private ThirdOauthUserService thirdOauthUserService() {
        return getBean(ThirdOauthUserService.class);
    }
}

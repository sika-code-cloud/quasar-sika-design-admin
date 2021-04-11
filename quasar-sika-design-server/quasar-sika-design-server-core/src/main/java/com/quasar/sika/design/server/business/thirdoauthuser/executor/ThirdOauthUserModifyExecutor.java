package com.quasar.sika.design.server.business.thirdoauthuser.executor;

import cn.hutool.core.bean.BeanUtil;
import com.quasar.sika.design.server.business.thirdoauthuser.context.ThirdOauthUserModifyContext;
import com.quasar.sika.design.server.business.thirdoauthuser.pojo.dto.ThirdOauthUserDTO;
import com.quasar.sika.design.server.business.thirdoauthuser.service.ThirdOauthUserService;
import com.sika.code.basic.pojo.dto.ServiceResult;
import com.sika.code.basic.util.Assert;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.standard.base.pojo.domain.BaseStandardDomain;
import com.sika.code.standard.base.pojo.executor.BaseStandardExecutor;
import lombok.Data;
import lombok.experimental.Accessors;
import me.zhyd.oauth.model.AuthUser;

/**
 * 第三方授权用户变更请求类
 */
@Data
@Accessors(chain = true)
public class ThirdOauthUserModifyExecutor extends BaseStandardExecutor<ThirdOauthUserModifyContext> implements BaseStandardDomain {
    @Override
    protected void executeBefore() {
        verify();
    }

    protected void verify() {
        AuthUser authUser = context.getAuthUser();
        // 断言校验
        Assert.verifyObjNull(authUser, "授权用户");
        // 断言校验uuid
        Assert.verifyStrEmpty(authUser.getUuid(), "授权用户UUID");
        // 断言校验source 来源
        Assert.verifyStrEmpty(authUser.getSource(), "授权用户【source】");
    }

    @Override
    protected ServiceResult doExecute() {
        AuthUser authUser = context.getAuthUser();
        ThirdOauthUserDTO oauthUserDTO = BeanUtil.copyProperties(authUser, ThirdOauthUserDTO.class);
        // 从数据库中查询用户是否存在
        ThirdOauthUserDTO oauthUserFromDb = getThirdOauthUserService().findByUuidAndSource(authUser.getUuid(), authUser.getSource());
        // 存在设置id
        if (BaseUtil.isNotNull(oauthUserFromDb)) {
            oauthUserDTO.setId(oauthUserFromDb.getId());
        }
        oauthUserDTO.setState(context.getState());
        ThirdOauthUserDTO oauthUserUpdated = getThirdOauthUserService().saveOrUpdateAndRet(oauthUserDTO);
        return ServiceResult.newInstanceOfSucResult(oauthUserUpdated);
    }

    private ThirdOauthUserService getThirdOauthUserService() {
        return getBean(ThirdOauthUserService.class);
    }

}

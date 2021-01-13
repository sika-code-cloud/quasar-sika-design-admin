package com.quasar.sika.design.server.business.thirdoauthuser.bo.request;

import cn.hutool.core.bean.BeanUtil;
import com.quasar.sika.design.server.business.thirdoauthuser.bo.response.ThirdOauthUserModifyResponseBO;
import com.quasar.sika.design.server.business.thirdoauthuser.pojo.dto.ThirdOauthUserDTO;
import com.quasar.sika.design.server.business.thirdoauthuser.service.ThirdOauthUserService;
import com.sika.code.basic.util.Assert;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.factory.BeanFactory;
import com.sika.code.standard.base.pojo.bo.request.BaseStandardRequestBO;
import com.sika.code.standard.base.pojo.domain.BaseStandardDomain;
import lombok.Data;
import lombok.experimental.Accessors;
import me.zhyd.oauth.model.AuthUser;

/**
 * 第三方授权用户变更请求类
 */
@Data
@Accessors(chain = true)
public class ThirdOauthUserModifyRequestBO extends BaseStandardRequestBO<ThirdOauthUserModifyResponseBO> implements BaseStandardDomain {
    private AuthUser authUser;
    private String state;

    @Override
    protected void init() {

    }

    @Override
    protected void verify() {
        // 断言校验
        Assert.verifyObjNull(authUser, "授权用户");
        // 断言校验uuid
        Assert.verifyStrEmpty(authUser.getUuid(), "授权用户UUID");
        // 断言校验source 来源
        Assert.verifyStrEmpty(authUser.getSource(), "授权用户【source】");
    }

    @Override
    protected ThirdOauthUserModifyResponseBO doExecute() {
        ThirdOauthUserDTO oauthUserDTO = BeanUtil.copyProperties(authUser, ThirdOauthUserDTO.class);
        // 从数据库中查询用户是否存在
        ThirdOauthUserDTO oauthUserFromDb = getThirdOauthUserService().findByUuidAndSource(authUser.getUuid(), authUser.getSource());
        // 存在设置id
        if (BaseUtil.isNotNull(oauthUserFromDb)) {
            oauthUserDTO.setId(oauthUserFromDb.getId());
        }
        oauthUserDTO.setState(this.state);
        ThirdOauthUserDTO oauthUserUpdated = getThirdOauthUserService().saveOrUpdateAndRet(oauthUserDTO);
        return BeanFactory.newResponseBO(this, oauthUserUpdated);
    }

    private ThirdOauthUserService getThirdOauthUserService() {
        return getBean(ThirdOauthUserService.class);
    }

    @Override
    public Class<ThirdOauthUserModifyResponseBO> responseClass() {
        return ThirdOauthUserModifyResponseBO.class;
    }

}

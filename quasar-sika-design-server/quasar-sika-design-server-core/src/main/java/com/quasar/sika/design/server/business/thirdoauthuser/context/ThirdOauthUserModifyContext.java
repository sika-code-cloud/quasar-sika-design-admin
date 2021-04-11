package com.quasar.sika.design.server.business.thirdoauthuser.context;

import com.quasar.sika.design.server.business.thirdoauthuser.executor.ThirdOauthUserModifyExecutor;
import com.sika.code.standard.base.pojo.context.BaseStandardContext;
import com.sika.code.standard.base.pojo.executor.BaseStandardExecutor;
import lombok.Data;
import lombok.experimental.Accessors;
import me.zhyd.oauth.model.AuthUser;

/**
 * 第三方授权用户变更请求类
 */
@Data
@Accessors(chain = true)
public class ThirdOauthUserModifyContext extends BaseStandardContext {
    private AuthUser authUser;
    private String state;

    @Override
    protected Class<? extends BaseStandardExecutor> buildExecutorClass() {
        return ThirdOauthUserModifyExecutor.class;
    }
}

package com.quasar.sika.design.server.business.thirdoauthuser.context;

import com.quasar.sika.design.server.business.thirdoauthuser.executor.ThirdOauthUserBindExecutor;
import com.quasar.sika.design.server.common.auth.pojo.request.BindOauthUserRequest;
import com.sika.code.standard.base.pojo.context.BaseStandardContext;
import com.sika.code.standard.base.pojo.executor.BaseStandardExecutor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ThirdOauthUserBindContext extends BaseStandardContext {
    /**
     * 绑定的request
     */
    private BindOauthUserRequest request;

    @Override
    protected Class<? extends BaseStandardExecutor> buildExecutorClass() {
        return ThirdOauthUserBindExecutor.class;
    }

}

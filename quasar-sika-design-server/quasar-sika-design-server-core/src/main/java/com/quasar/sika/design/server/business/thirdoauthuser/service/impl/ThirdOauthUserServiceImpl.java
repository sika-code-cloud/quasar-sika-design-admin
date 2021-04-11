package com.quasar.sika.design.server.business.thirdoauthuser.service.impl;

import cn.hutool.core.util.StrUtil;
import com.quasar.sika.design.server.business.thirdoauthuser.context.ThirdOauthUserModifyContext;
import com.quasar.sika.design.server.business.thirdoauthuser.convert.ThirdOauthUserConvert;
import com.quasar.sika.design.server.business.thirdoauthuser.entity.ThirdOauthUserEntity;
import com.quasar.sika.design.server.business.thirdoauthuser.mapper.ThirdOauthUserMapper;
import com.quasar.sika.design.server.business.thirdoauthuser.pojo.dto.ThirdOauthUserDTO;
import com.quasar.sika.design.server.business.thirdoauthuser.pojo.query.ThirdOauthUserQuery;
import com.quasar.sika.design.server.business.thirdoauthuser.service.ThirdOauthUserService;
import com.quasar.sika.design.server.common.executor.manager.ExecutorManager;
import com.sika.code.basic.pojo.dto.ServiceResult;
import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import me.zhyd.oauth.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 第三方授权用户表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-01 23:24:33
 */
@Service(value = "thirdOauthUserService")
public class ThirdOauthUserServiceImpl extends BaseStandardServiceImpl<ThirdOauthUserMapper, ThirdOauthUserEntity, ThirdOauthUserDTO> implements ThirdOauthUserService {
    @Autowired
    private ThirdOauthUserMapper thirdOauthUserMapper;
    @Autowired
    private ExecutorManager executorManager;

    @Override
    public ThirdOauthUserDTO findByUuidAndSource(String uuid, String source) {
        if (StrUtil.isBlank(uuid) || StrUtil.isBlank(source)) {
            return null;
        }
        return find(new ThirdOauthUserQuery().setUuid(uuid).setSource(source));
    }

    @Override
    public ThirdOauthUserDTO findByStateAndSource(String state, String source) {
        if (StrUtil.isBlank(state) || StrUtil.isBlank(source)) {
            return null;
        }
        return find(new ThirdOauthUserQuery().setState(state).setSource(source.toUpperCase()));
    }

    @Override
    public ThirdOauthUserDTO modifyByAuthUser(AuthUser authUser, String state) {
        // 参数设置
        ThirdOauthUserModifyContext context = new ThirdOauthUserModifyContext().setAuthUser(authUser).setState(state);
        // 执行
        ServiceResult<ThirdOauthUserDTO> serviceResult = executorManager.execute(context);
        // 响应
        return serviceResult.getResult();
    }

    @Override
    protected BaseConvert<ThirdOauthUserEntity, ThirdOauthUserDTO> convert() {
        return ThirdOauthUserConvert.INSTANCE;
    }
}


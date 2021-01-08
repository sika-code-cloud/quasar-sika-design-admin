package com.quasar.sika.design.server.business.thirdoauthuser.service.impl;

import cn.hutool.core.util.StrUtil;
import com.quasar.sika.design.server.business.thirdoauthuser.bo.request.ThirdOauthUserModifyRequestBO;
import com.quasar.sika.design.server.business.thirdoauthuser.bo.response.ThirdOauthUserModifyResponseBO;
import com.quasar.sika.design.server.business.thirdoauthuser.convert.ThirdOauthUserConvert;
import com.quasar.sika.design.server.business.thirdoauthuser.entity.ThirdOauthUserEntity;
import com.quasar.sika.design.server.business.thirdoauthuser.mapper.ThirdOauthUserMapper;
import com.quasar.sika.design.server.business.thirdoauthuser.pojo.dto.ThirdOauthUserDTO;
import com.quasar.sika.design.server.business.thirdoauthuser.pojo.query.ThirdOauthUserQuery;
import com.quasar.sika.design.server.business.thirdoauthuser.service.ThirdOauthUserService;
import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.executor.DomainExecutor;
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

    @Override
    public ThirdOauthUserDTO findByUuidAndSource(String uuid, String source) {
        if (StrUtil.isBlank(uuid) || StrUtil.isBlank(source)) {
            return null;
        }
        return find(new ThirdOauthUserQuery().setUuid(uuid).setSource(source));
    }

    @Override
    public ThirdOauthUserDTO modifyByAuthUser(AuthUser authUser) {
        // 参数设置
        ThirdOauthUserModifyRequestBO request = new ThirdOauthUserModifyRequestBO().setAuthUser(authUser);
        // 执行
        ThirdOauthUserModifyResponseBO responseBO = DomainExecutor.execute(request);
        // 响应
        return responseBO.getOauthUserDTO();
    }

    @Override
    protected BaseConvert<ThirdOauthUserEntity, ThirdOauthUserDTO> convert() {
        return ThirdOauthUserConvert.INSTANCE;
    }
}


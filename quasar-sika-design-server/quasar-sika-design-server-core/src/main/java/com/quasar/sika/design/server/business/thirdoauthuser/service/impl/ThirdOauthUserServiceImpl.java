package com.quasar.sika.design.server.business.thirdoauthuser.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.quasar.sika.design.server.business.thirdoauthuser.convert.ThirdOauthUserConvert;
import com.quasar.sika.design.server.business.thirdoauthuser.entity.ThirdOauthUserEntity;
import com.quasar.sika.design.server.business.thirdoauthuser.mapper.ThirdOauthUserMapper;
import com.quasar.sika.design.server.business.thirdoauthuser.pojo.dto.ThirdOauthUserDTO;
import com.quasar.sika.design.server.business.thirdoauthuser.pojo.query.ThirdOauthUserQuery;
import com.quasar.sika.design.server.business.thirdoauthuser.service.ThirdOauthUserService;
import com.sika.code.basic.util.Assert;
import com.sika.code.basic.util.BaseUtil;
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

    @Override
    public ThirdOauthUserDTO modifyByAuthUser(AuthUser authUser) {
        // 断言校验
        Assert.verifyObjNull(authUser, "授权用户");
        // 断言校验uuid
        Assert.verifyStrEmpty(authUser.getUuid(), "授权用户UUID");
        // 断言校验source 来源
        Assert.verifyStrEmpty(authUser.getSource(), "授权用户【source】");
        // 转化
        ThirdOauthUserDTO oauthUserDTO = BeanUtil.copyProperties(authUser, ThirdOauthUserDTO.class);
        // 从数据库中查询用户是否存在
        ThirdOauthUserQuery query = new ThirdOauthUserQuery()
                .setUuid(authUser.getUuid())
                .setSource(authUser.getSource());
        ThirdOauthUserDTO oauthUserFromDb = find(query);
        if (BaseUtil.isNotNull(oauthUserFromDb)) {
            oauthUserDTO.setId(oauthUserFromDb.getId());
        }
        return saveOrUpdateAndRet(oauthUserDTO);
    }

    @Override
    protected BaseConvert<ThirdOauthUserEntity, ThirdOauthUserDTO> convert() {
        return ThirdOauthUserConvert.INSTANCE;
    }
}


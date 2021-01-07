package com.quasar.sika.design.server.business.useronline.service.impl;

import com.quasar.sika.design.server.business.useronline.entity.UserOnlineEntity;
import com.quasar.sika.design.server.business.useronline.mapper.UserOnlineMapper;
import com.quasar.sika.design.server.business.useronline.pojo.dto.UserOnlineDTO;
import com.quasar.sika.design.server.business.useronline.service.UserOnlineService;
import com.quasar.sika.design.server.business.useronline.convert.UserOnlineConvert;

import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 在线用户记录 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:36:31
 */
@Service(value = "userOnlineService")
public class UserOnlineServiceImpl extends BaseStandardServiceImpl<UserOnlineMapper, UserOnlineEntity, UserOnlineDTO> implements UserOnlineService {
    @Autowired
    private UserOnlineMapper userOnlineMapper;


    @Override
    protected BaseConvert<UserOnlineEntity, UserOnlineDTO> convert() {
        return UserOnlineConvert.INSTANCE;
    }
}


package com.quasar.sika.design.server.business.userpost.service.impl;

import com.quasar.sika.design.server.business.userpost.entity.UserPostEntity;
import com.quasar.sika.design.server.business.userpost.mapper.UserPostMapper;
import com.quasar.sika.design.server.business.userpost.pojo.dto.UserPostDTO;
import com.quasar.sika.design.server.business.userpost.service.UserPostService;
import com.quasar.sika.design.server.business.userpost.convert.UserPostConvert;

import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与岗位关联表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-08 00:03:43
 */
@Service(value = "userPostService")
public class UserPostServiceImpl extends BaseStandardServiceImpl<UserPostMapper, UserPostEntity, UserPostDTO> implements UserPostService {
    @Autowired
    private UserPostMapper userPostMapper;


    @Override
    protected BaseConvert<UserPostEntity, UserPostDTO> convert() {
        return UserPostConvert.INSTANCE;
    }
}


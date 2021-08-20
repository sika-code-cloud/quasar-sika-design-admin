package com.quasar.sika.design.server.business.testuser.service.impl;

import com.quasar.sika.design.server.business.testuser.entity.TestUserEntity;
import com.quasar.sika.design.server.business.testuser.mapper.TestUserMapper;
import com.quasar.sika.design.server.business.testuser.pojo.dto.TestUserDTO;
import com.quasar.sika.design.server.business.testuser.service.TestUserService;
import com.quasar.sika.design.server.business.testuser.convert.TestUserConvert;

import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试用户表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2021-07-03 15:44:05
 */
@Service(value = "testUser0Service")
public class TestUserServiceImpl extends BaseStandardServiceImpl<TestUserMapper, TestUserEntity, TestUserDTO> implements TestUserService {
    @Autowired
    private TestUserMapper testUserMapper;


    @Override
    protected BaseConvert<TestUserEntity, TestUserDTO> convert() {
        return TestUserConvert.INSTANCE;
    }
}


package com.quasar.sika.design.server.business.testorder.service.impl;

import com.quasar.sika.design.server.business.testorder.entity.TestOrderEntity;
import com.quasar.sika.design.server.business.testorder.mapper.TestOrderMapper;
import com.quasar.sika.design.server.business.testorder.pojo.dto.TestOrderDTO;
import com.quasar.sika.design.server.business.testorder.service.TestOrderService;
import com.quasar.sika.design.server.business.testorder.convert.TestOrderConvert;

import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试订单表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2021-07-03 15:41:31
 */
@Service(value = "testOrderService")
public class TestOrderServiceImpl extends BaseStandardServiceImpl<TestOrderMapper, TestOrderEntity, TestOrderDTO> implements TestOrderService {
    @Autowired
    private TestOrderMapper testOrderMapper;


    @Override
    protected BaseConvert<TestOrderEntity, TestOrderDTO> convert() {
        return TestOrderConvert.INSTANCE;
    }
}


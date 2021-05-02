package com.quasar.sika.design.server.business.demo.service.impl;

import com.quasar.sika.design.server.business.demo.convert.DemoConvert;
import com.quasar.sika.design.server.business.demo.entity.DemoEntity;
import com.quasar.sika.design.server.business.demo.mapper.DemoMapper;
import com.quasar.sika.design.server.business.demo.pojo.dto.DemoDTO;
import com.quasar.sika.design.server.business.demo.service.DemoService;

import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 示例表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-22 23:55:45
 */
@Service(value = "demoService")
public class DemoServiceImpl extends BaseStandardServiceImpl<DemoMapper, DemoEntity, DemoDTO> implements DemoService {
    @Autowired
    private DemoMapper demoMapper;


    @Override
    protected BaseConvert<DemoEntity, DemoDTO> convert() {
        return DemoConvert.INSTANCE;
    }
}


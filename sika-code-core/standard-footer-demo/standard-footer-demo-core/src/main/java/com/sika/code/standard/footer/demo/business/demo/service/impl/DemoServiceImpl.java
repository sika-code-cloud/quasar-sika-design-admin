package com.sika.code.standard.footer.demo.business.demo.service.impl;

import com.sika.code.standard.footer.demo.business.demo.entity.DemoEntity;
import com.sika.code.standard.footer.demo.business.demo.mapper.DemoMapper;
import com.sika.code.standard.footer.demo.business.demo.pojo.dto.DemoDTO;
import com.sika.code.standard.footer.demo.business.demo.service.DemoService;
import com.sika.code.standard.footer.demo.business.demo.convert.DemoConvert;

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
 * @since 2020-12-22 12:22:11
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


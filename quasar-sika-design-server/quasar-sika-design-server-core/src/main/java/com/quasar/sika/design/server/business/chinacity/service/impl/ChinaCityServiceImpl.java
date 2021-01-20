package com.quasar.sika.design.server.business.chinacity.service.impl;

import com.quasar.sika.design.server.business.chinacity.entity.ChinaCityEntity;
import com.quasar.sika.design.server.business.chinacity.mapper.ChinaCityMapper;
import com.quasar.sika.design.server.business.chinacity.pojo.dto.ChinaCityDTO;
import com.quasar.sika.design.server.business.chinacity.service.ChinaCityService;
import com.quasar.sika.design.server.business.chinacity.convert.ChinaCityConvert;

import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 参数配置表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-20 13:01:42
 */
@Service(value = "chinaCityService")
public class ChinaCityServiceImpl extends BaseStandardServiceImpl<ChinaCityMapper, ChinaCityEntity, ChinaCityDTO> implements ChinaCityService {
    @Autowired
    private ChinaCityMapper chinaCityMapper;


    @Override
    protected BaseConvert<ChinaCityEntity, ChinaCityDTO> convert() {
        return ChinaCityConvert.INSTANCE;
    }
}


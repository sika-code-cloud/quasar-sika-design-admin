package com.quasar.sika.design.server.business.config.service.impl;

import com.quasar.sika.design.server.business.config.entity.ConfigEntity;
import com.quasar.sika.design.server.business.config.mapper.ConfigMapper;
import com.quasar.sika.design.server.business.config.pojo.dto.ConfigDTO;
import com.quasar.sika.design.server.business.config.service.ConfigService;
import com.quasar.sika.design.server.business.config.convert.ConfigConvert;

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
 * @since 2021-01-07 23:34:06
 */
@Service(value = "configService")
public class ConfigServiceImpl extends BaseStandardServiceImpl<ConfigMapper, ConfigEntity, ConfigDTO> implements ConfigService {
    @Autowired
    private ConfigMapper configMapper;


    @Override
    protected BaseConvert<ConfigEntity, ConfigDTO> convert() {
        return ConfigConvert.INSTANCE;
    }
}


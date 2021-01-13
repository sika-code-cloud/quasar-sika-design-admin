package com.quasar.sika.design.server.business.dictdata.service.impl;

import com.quasar.sika.design.server.business.dictdata.entity.DictDataEntity;
import com.quasar.sika.design.server.business.dictdata.mapper.DictDataMapper;
import com.quasar.sika.design.server.business.dictdata.pojo.dto.DictDataDTO;
import com.quasar.sika.design.server.business.dictdata.service.DictDataService;
import com.quasar.sika.design.server.business.dictdata.convert.DictDataConvert;

import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典数据表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:34:36
 */
@Service(value = "dictDataService")
public class DictDataServiceImpl extends BaseStandardServiceImpl<DictDataMapper, DictDataEntity, DictDataDTO> implements DictDataService {
    @Autowired
    private DictDataMapper dictDataMapper;


    @Override
    protected BaseConvert<DictDataEntity, DictDataDTO> convert() {
        return DictDataConvert.INSTANCE;
    }
}


package com.quasar.sika.design.server.business.dicttype.service.impl;

import com.quasar.sika.design.server.business.dicttype.entity.DictTypeEntity;
import com.quasar.sika.design.server.business.dicttype.mapper.DictTypeMapper;
import com.quasar.sika.design.server.business.dicttype.pojo.dto.DictTypeDTO;
import com.quasar.sika.design.server.business.dicttype.service.DictTypeService;
import com.quasar.sika.design.server.business.dicttype.convert.DictTypeConvert;

import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典类型表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:34:47
 */
@Service(value = "dictTypeService")
public class DictTypeServiceImpl extends BaseStandardServiceImpl<DictTypeMapper, DictTypeEntity, DictTypeDTO> implements DictTypeService {
    @Autowired
    private DictTypeMapper dictTypeMapper;


    @Override
    protected BaseConvert<DictTypeEntity, DictTypeDTO> convert() {
        return DictTypeConvert.INSTANCE;
    }
}


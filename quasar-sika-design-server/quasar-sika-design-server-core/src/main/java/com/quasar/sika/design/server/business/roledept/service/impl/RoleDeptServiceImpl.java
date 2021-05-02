package com.quasar.sika.design.server.business.roledept.service.impl;

import com.quasar.sika.design.server.business.roledept.entity.RoleDeptEntity;
import com.quasar.sika.design.server.business.roledept.mapper.RoleDeptMapper;
import com.quasar.sika.design.server.business.roledept.pojo.dto.RoleDeptDTO;
import com.quasar.sika.design.server.business.roledept.service.RoleDeptService;
import com.quasar.sika.design.server.business.roledept.convert.RoleDeptConvert;

import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色和部门关联表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-08 00:03:11
 */
@Service(value = "roleDeptService")
public class RoleDeptServiceImpl extends BaseStandardServiceImpl<RoleDeptMapper, RoleDeptEntity, RoleDeptDTO> implements RoleDeptService {
    @Autowired
    private RoleDeptMapper roleDeptMapper;


    @Override
    protected BaseConvert<RoleDeptEntity, RoleDeptDTO> convert() {
        return RoleDeptConvert.INSTANCE;
    }
}


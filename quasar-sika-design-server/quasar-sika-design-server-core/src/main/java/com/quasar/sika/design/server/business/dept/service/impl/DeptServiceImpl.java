package com.quasar.sika.design.server.business.dept.service.impl;

import com.quasar.sika.design.server.business.dept.entity.DeptEntity;
import com.quasar.sika.design.server.business.dept.mapper.DeptMapper;
import com.quasar.sika.design.server.business.dept.pojo.dto.DeptDTO;
import com.quasar.sika.design.server.business.dept.service.DeptService;
import com.quasar.sika.design.server.business.dept.convert.DeptConvert;

import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:34:24
 */
@Service(value = "deptService")
public class DeptServiceImpl extends BaseStandardServiceImpl<DeptMapper, DeptEntity, DeptDTO> implements DeptService {
    @Autowired
    private DeptMapper deptMapper;


    @Override
    protected BaseConvert<DeptEntity, DeptDTO> convert() {
        return DeptConvert.INSTANCE;
    }
}


package com.quasar.sika.design.server.business.operlog.service.impl;

import com.quasar.sika.design.server.business.operlog.entity.OperLogEntity;
import com.quasar.sika.design.server.business.operlog.mapper.OperLogMapper;
import com.quasar.sika.design.server.business.operlog.pojo.dto.OperLogDTO;
import com.quasar.sika.design.server.business.operlog.service.OperLogService;
import com.quasar.sika.design.server.business.operlog.convert.OperLogConvert;

import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:34
 */
@Service(value = "operLogService")
public class OperLogServiceImpl extends BaseStandardServiceImpl<OperLogMapper, OperLogEntity, OperLogDTO> implements OperLogService {
    @Autowired
    private OperLogMapper operLogMapper;


    @Override
    protected BaseConvert<OperLogEntity, OperLogDTO> convert() {
        return OperLogConvert.INSTANCE;
    }
}


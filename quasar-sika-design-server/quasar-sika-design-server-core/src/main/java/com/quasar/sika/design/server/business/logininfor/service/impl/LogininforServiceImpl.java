package com.quasar.sika.design.server.business.logininfor.service.impl;

import com.quasar.sika.design.server.business.logininfor.entity.LogininforEntity;
import com.quasar.sika.design.server.business.logininfor.mapper.LogininforMapper;
import com.quasar.sika.design.server.business.logininfor.pojo.dto.LogininforDTO;
import com.quasar.sika.design.server.business.logininfor.service.LogininforService;
import com.quasar.sika.design.server.business.logininfor.convert.LogininforConvert;

import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统访问记录 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:34:58
 */
@Service(value = "logininforService")
public class   LogininforServiceImpl extends BaseStandardServiceImpl<LogininforMapper, LogininforEntity, LogininforDTO> implements LogininforService {
    @Autowired
    private LogininforMapper logininforMapper;


    @Override
    protected BaseConvert<LogininforEntity, LogininforDTO> convert() {
        return LogininforConvert.INSTANCE;
    }
}


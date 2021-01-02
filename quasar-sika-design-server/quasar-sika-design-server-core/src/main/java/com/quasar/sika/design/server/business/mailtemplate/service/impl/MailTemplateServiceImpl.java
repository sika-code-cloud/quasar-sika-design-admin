package com.quasar.sika.design.server.business.mailtemplate.service.impl;

import cn.hutool.core.util.StrUtil;
import com.quasar.sika.design.server.business.mailtemplate.entity.MailTemplateEntity;
import com.quasar.sika.design.server.business.mailtemplate.mapper.MailTemplateMapper;
import com.quasar.sika.design.server.business.mailtemplate.pojo.dto.MailTemplateDTO;
import com.quasar.sika.design.server.business.mailtemplate.pojo.query.MailTemplateQuery;
import com.quasar.sika.design.server.business.mailtemplate.service.MailTemplateService;
import com.quasar.sika.design.server.business.mailtemplate.convert.MailTemplateConvert;

import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 邮件模板表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-02 21:24:10
 */
@Service(value = "mailTemplateService")
public class MailTemplateServiceImpl extends BaseStandardServiceImpl<MailTemplateMapper, MailTemplateEntity, MailTemplateDTO> implements MailTemplateService {
    @Autowired
    private MailTemplateMapper mailTemplateMapper;

    @Override
    public MailTemplateDTO findByCode(String code) {
        if (StrUtil.isBlank(code)) {
            return null;
        }
        MailTemplateQuery query = new MailTemplateQuery().setCode(code);
        return find(query);
    }

    @Override
    protected BaseConvert<MailTemplateEntity, MailTemplateDTO> convert() {
        return MailTemplateConvert.INSTANCE;
    }
}


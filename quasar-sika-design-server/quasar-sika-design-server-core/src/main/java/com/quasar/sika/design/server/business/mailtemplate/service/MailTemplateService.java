package com.quasar.sika.design.server.business.mailtemplate.service;

import com.sika.code.standard.base.service.BaseStandardService;
import com.quasar.sika.design.server.business.mailtemplate.pojo.dto.MailTemplateDTO;

/**
 * <p>
 * 邮件模板表 服务类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-02 21:24:09
 */
public interface MailTemplateService extends BaseStandardService<MailTemplateDTO> {
    /**
     * 根据code查询邮件模板数据
     * @param code
     * @return
     */
    MailTemplateDTO findByCode(String code);
    /**
     * 根据type查询邮件模板数据
     * @param type
     * @return
     */
    MailTemplateDTO findByType(Integer type);
}

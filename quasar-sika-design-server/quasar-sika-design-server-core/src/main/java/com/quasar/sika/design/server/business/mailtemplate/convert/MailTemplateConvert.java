package com.quasar.sika.design.server.business.mailtemplate.convert;

import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.quasar.sika.design.server.business.mailtemplate.entity.MailTemplateEntity;
import com.quasar.sika.design.server.business.mailtemplate.pojo.dto.MailTemplateDTO;

/**
 * <p>
 * 邮件模板表 转换类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-02 21:24:11
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MailTemplateConvert extends BaseConvert<MailTemplateEntity, MailTemplateDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    MailTemplateConvert INSTANCE = Mappers.getMapper(MailTemplateConvert.class);

}

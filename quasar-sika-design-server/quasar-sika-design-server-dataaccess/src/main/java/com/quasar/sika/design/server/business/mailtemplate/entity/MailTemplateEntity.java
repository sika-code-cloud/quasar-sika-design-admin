package com.quasar.sika.design.server.business.mailtemplate.entity;

import java.io.Serializable;
import com.sika.code.standard.base.pojo.entity.BaseStandardEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 邮件模板表
 * </p>
 *
 * @author daiqi
 * @since 2021-01-02 21:24:07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sika_mail_template")
public class MailTemplateEntity extends BaseStandardEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模板类型
     */
    private Integer type;

    /**
     * 模板编号
     */
    private String code;

    /**
     * 模板主题
     */
    private String title;

    /**
     * 模板内容
     */
    private String content;


}

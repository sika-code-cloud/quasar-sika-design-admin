package com.quasar.sika.design.server.business.mailtemplate.pojo.dto;

import java.io.Serializable;
import com.sika.code.standard.base.pojo.dto.BaseStandardDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 邮件模板表
 * </p>
 *
 * @author daiqi
 * @since 2021-01-02 21:24:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class MailTemplateDTO extends BaseStandardDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据表id
     */
    private Long mailTemplateId;
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

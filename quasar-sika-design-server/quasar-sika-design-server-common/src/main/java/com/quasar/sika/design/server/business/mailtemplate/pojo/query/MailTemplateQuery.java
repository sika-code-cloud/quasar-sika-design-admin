package com.quasar.sika.design.server.business.mailtemplate.pojo.query;

import com.sika.code.standard.base.pojo.query.BaseStandardQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 邮件模板表 查询类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-02 21:24:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class MailTemplateQuery extends BaseStandardQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据表id
     */
    protected Long mailTemplateId;
    /**
     * 模板类型
     */
    protected Integer type;
    /**
     * 模板编号
     */
    protected String code;
    /**
     * 模板主题
     */
    protected String title;
    /**
     * 模板内容
     */
    protected String content;
    /**
     * id列表
     */
    protected Set<Long> ids;

}


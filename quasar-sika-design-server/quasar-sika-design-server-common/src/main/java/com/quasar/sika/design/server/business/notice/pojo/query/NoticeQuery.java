package com.quasar.sika.design.server.business.notice.pojo.query;

import com.sika.code.standard.base.pojo.query.BaseStandardQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 通知公告表 查询类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class NoticeQuery extends BaseStandardQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据表id
     */
    protected Long noticeId;
    /**
     * 公告标题
     */
    protected String noticeTitle;
    /**
     * 公告类型（1通知 2公告）
     */
    protected String noticeType;
    /**
     * 公告内容
     */
    protected String noticeContent;
    /**
     * 公告状态（0正常 1关闭）
     */
    protected String status;
    /**
     * id列表
     */
    protected Set<Long> ids;

}


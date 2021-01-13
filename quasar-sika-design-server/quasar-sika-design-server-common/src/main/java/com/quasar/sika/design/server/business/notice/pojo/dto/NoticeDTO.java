package com.quasar.sika.design.server.business.notice.pojo.dto;

import java.io.Serializable;
import com.sika.code.standard.base.pojo.dto.BaseStandardDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 通知公告表
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class NoticeDTO extends BaseStandardDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据表id
     */
    private Long noticeId;
    /**
     * 公告标题
     */
    private String noticeTitle;
    /**
     * 公告类型（1通知 2公告）
     */
    private String noticeType;
    /**
     * 公告内容
     */
    private String noticeContent;
    /**
     * 公告状态（0正常 1关闭）
     */
    private String status;

}

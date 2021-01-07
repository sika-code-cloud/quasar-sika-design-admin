package com.quasar.sika.design.server.business.logininfor.pojo.dto;

import java.time.LocalDateTime;
import java.io.Serializable;
import com.sika.code.standard.base.pojo.dto.BaseStandardDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统访问记录
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:34:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class LogininforDTO extends BaseStandardDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据表id
     */
    private Long logininforId;
    /**
     * 登录账号
     */
    private String loginName;
    /**
     * 登录IP地址
     */
    private String ipaddr;
    /**
     * 登录地点
     */
    private String loginLocation;
    /**
     * 浏览器类型
     */
    private String browser;
    /**
     * 操作系统
     */
    private String os;
    /**
     * 登录状态（0成功 1失败）
     */
    private String status;
    /**
     * 提示消息
     */
    private String msg;
    /**
     * 访问时间
     */
    private LocalDateTime loginTime;

}

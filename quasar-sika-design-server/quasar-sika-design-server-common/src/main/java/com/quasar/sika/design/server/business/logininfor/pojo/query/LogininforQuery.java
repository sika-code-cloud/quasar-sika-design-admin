package com.quasar.sika.design.server.business.logininfor.pojo.query;

import com.sika.code.standard.base.pojo.query.BaseStandardQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * <p>
 * 系统访问记录 查询类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:34:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class LogininforQuery extends BaseStandardQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据表id
     */
    protected Long logininforId;
    /**
     * 登录账号
     */
    protected String loginName;
    /**
     * 登录IP地址
     */
    protected String ipaddr;
    /**
     * 登录地点
     */
    protected String loginLocation;
    /**
     * 浏览器类型
     */
    protected String browser;
    /**
     * 操作系统
     */
    protected String os;
    /**
     * 登录状态（0成功 1失败）
     */
    protected String status;
    /**
     * 提示消息
     */
    protected String msg;
    /**
     * 访问时间
     */
    protected LocalDateTime loginTime;
    /**
     * id列表
     */
    protected Set<Long> ids;

}


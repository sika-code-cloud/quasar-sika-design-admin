package com.quasar.sika.design.server.business.useronline.pojo.query;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.sika.code.standard.base.pojo.query.BaseStandardQuery;

/**
 * <p>
 * 在线用户记录 查询类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-08 00:03:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserOnlineQuery extends BaseStandardQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据表id
     */
    protected Long userOnlineId;
    /**
     * 用户会话id
     */
    protected String sessionid;
    /**
     * 登录账号
     */
    protected String loginName;
    /**
     * 部门名称
     */
    protected String deptName;
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
     * 在线状态on_line在线off_line离线
     */
    protected String status;
    /**
     * session创建时间
     */
    protected LocalDateTime startTimestamp;
    /**
     * session最后访问时间
     */
    protected LocalDateTime lastAccessTime;
    /**
     * 超时时间，单位为分钟
     */
    protected Integer expireTime;
    /**
     * id列表
     */
    protected Set<Long> ids;

}


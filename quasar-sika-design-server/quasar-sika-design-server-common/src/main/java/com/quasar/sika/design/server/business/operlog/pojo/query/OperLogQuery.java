package com.quasar.sika.design.server.business.operlog.pojo.query;

import com.sika.code.standard.base.pojo.query.BaseStandardQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * <p>
 * 操作日志记录 查询类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class OperLogQuery extends BaseStandardQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据表id
     */
    protected Long operLogId;
    /**
     * 模块标题
     */
    protected String title;
    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    protected Integer businessType;
    /**
     * 方法名称
     */
    protected String method;
    /**
     * 请求方式
     */
    protected String requestMethod;
    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    protected Integer operatorType;
    /**
     * 操作人员
     */
    protected String operName;
    /**
     * 部门名称
     */
    protected String deptName;
    /**
     * 请求URL
     */
    protected String operUrl;
    /**
     * 主机地址
     */
    protected String operIp;
    /**
     * 操作地点
     */
    protected String operLocation;
    /**
     * 请求参数
     */
    protected String operParam;
    /**
     * 返回参数
     */
    protected String jsonResult;
    /**
     * 操作状态（0正常 1异常）
     */
    protected Integer status;
    /**
     * 错误消息
     */
    protected String errorMsg;
    /**
     * 操作时间
     */
    protected LocalDateTime operTime;
    /**
     * id列表
     */
    protected Set<Long> ids;

}


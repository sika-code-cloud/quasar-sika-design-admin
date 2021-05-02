package com.quasar.sika.design.server.business.dept.pojo.query;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.sika.code.standard.base.pojo.query.BaseStandardQuery;

/**
 * <p>
 * 部门表 查询类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:34:17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class DeptQuery extends BaseStandardQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据表id
     */
    protected Long deptId;
    /**
     * 父部门id
     */
    protected Long parentId;
    /**
     * 祖级列表
     */
    protected String ancestors;
    /**
     * 部门名称
     */
    protected String deptName;
    /**
     * 显示顺序
     */
    protected Integer orderNum;
    /**
     * 负责人
     */
    protected String leader;
    /**
     * 联系电话
     */
    protected String phone;
    /**
     * 邮箱
     */
    protected String email;
    /**
     * id列表
     */
    protected Set<Long> ids;

}


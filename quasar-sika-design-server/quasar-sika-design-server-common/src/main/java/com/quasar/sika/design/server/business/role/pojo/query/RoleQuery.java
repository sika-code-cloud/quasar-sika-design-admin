package com.quasar.sika.design.server.business.role.pojo.query;

import com.sika.code.standard.base.pojo.query.BaseStandardQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 角色信息表 查询类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RoleQuery extends BaseStandardQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据表id
     */
    protected Long roleId;
    /**
     * 角色名称
     */
    protected String roleName;
    /**
     * 角色权限字符串
     */
    protected String roleKey;
    /**
     * 显示顺序
     */
    protected Integer roleSort;
    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    protected String dataScope;
    /**
     * id列表
     */
    protected Set<Long> ids;

}


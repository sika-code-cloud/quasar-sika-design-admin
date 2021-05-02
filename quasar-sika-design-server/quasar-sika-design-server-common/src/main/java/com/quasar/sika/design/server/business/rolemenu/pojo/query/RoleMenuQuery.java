package com.quasar.sika.design.server.business.rolemenu.pojo.query;

import java.io.Serializable;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.sika.code.standard.base.pojo.query.BaseStandardQuery;

/**
 * <p>
 * 角色和菜单关联表 查询类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-08 00:03:16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RoleMenuQuery extends BaseStandardQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据表id
     */
    protected Long roleMenuId;
    /**
     * 角色ID
     */
    protected Long roleId;
    /**
     * 菜单ID
     */
    protected Long menuId;
    /**
     * id列表
     */
    protected Set<Long> ids;

}


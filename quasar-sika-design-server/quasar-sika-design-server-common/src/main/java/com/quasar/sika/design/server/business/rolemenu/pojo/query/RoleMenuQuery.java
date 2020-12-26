package com.quasar.sika.design.server.business.rolemenu.pojo.query;

import com.sika.code.standard.base.pojo.query.BaseStandardQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 角色菜单表 查询类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:41
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
     * 菜单id
     */
    protected Long menuId;
    /**
     * 角色id
     */
    protected Long roleId;

    protected Set<Long> ids;
    protected Set<Long> roleIds;
    protected Set<Long> menuIds;
}


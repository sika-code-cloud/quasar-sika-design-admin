package com.quasar.sika.design.server.common.shiro.system.entity;

import java.io.Serializable;

import com.sika.code.database.common.entity.BaseEntity;
import lombok.Data;

/**
 * <p>  系统管理 - 角色-菜单关联表  </p>
 *
 * @author: zhengqing
 * @date: 2019-08-20
 */
@Data
public class RoleMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Integer id;
    /**
     * 角色ID
     */
	private Integer roleId;
    /**
     * 菜单ID
     */
	private Integer menuId;


}

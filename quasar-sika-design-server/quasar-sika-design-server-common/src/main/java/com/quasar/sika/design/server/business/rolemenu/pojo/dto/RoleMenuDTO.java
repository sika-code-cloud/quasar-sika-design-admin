package com.quasar.sika.design.server.business.rolemenu.pojo.dto;

import java.io.Serializable;
import com.sika.code.standard.base.pojo.dto.BaseStandardDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色菜单表
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RoleMenuDTO extends BaseStandardDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据表id
     */
    private Long roleMenuId;
    /**
     * 菜单id
     */
    private Long menuId;
    /**
     * 角色id
     */
    private Long roleId;

}

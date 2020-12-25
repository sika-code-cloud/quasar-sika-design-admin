package com.quasar.sika.design.server.common.shiro.system.entity;

import com.sika.code.database.common.entity.BaseEntity;
import lombok.Data;

/**
 * <p>  系统管理 - 用户角色关联表  </p>
 *
 * @author: zhengqing
 * @date: 2019-08-20
 */
@Data
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 角色ID
     */
    private Integer roleId;

}

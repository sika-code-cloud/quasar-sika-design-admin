package com.quasar.sika.design.server.common.shiro.system.entity;

import com.sika.code.database.common.entity.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * <p>  系统管理-角色表  </p>
 *
 * @author: zhengqing
 * @date: 2019-08-20
 */
@Data
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 角色编码
     */
    @NotBlank(message = "角色编码不能为空")
    @Length(max = 20, message = "角色编码不能超过20个字符")
    private String code;
    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String name;
    /**
     * 角色描述
     */
    private String remarks;


}

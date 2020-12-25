package com.quasar.sika.design.server.common.shiro.system.entity;

import com.sika.code.database.common.entity.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>  系统管理-权限菜单表  </p>
 *
 * @author: zhengqing
 * @date: 2019-08-19
 */
@Data
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Integer id;
    /**
     * 上级菜单ID
     */
	private String parentId;
	/**
	 * url
	 */
	private String url;
    /**
     * 菜单编码
     */
	@NotBlank(message = "菜单编码不能为空")
	@Length(max = 100, message = "菜单编码不能超过100个字符")
	private String resources;
    /**
     * 菜单名称
     */
	@NotBlank(message = "菜单名称不能为空")
	private String title;
    /**
     * 菜单级别
     */
	private Integer level;
    /**
     * 排序
     */
	private Integer sortNo;
    /**
     * 菜单图标
     */
	private String icon;
    /**
     * 类型 menu、button
     */
	@NotBlank(message = "类型不能为空")
	private String type;
    /**
     * 备注
     */
	private String remarks;


}

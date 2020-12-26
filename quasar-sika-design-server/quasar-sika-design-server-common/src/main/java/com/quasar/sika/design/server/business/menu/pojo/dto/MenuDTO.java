package com.quasar.sika.design.server.business.menu.pojo.dto;

import com.sika.code.standard.base.pojo.dto.BaseStandardDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class MenuDTO extends BaseStandardDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据表id
     */
    private Long menuId;
    /**
     * 父菜单id
     */
    private Long parentId;
    /**
     * 菜单路径
     */
    private String path;
    /**
     * 权限字段
     */
    private String resources;
    /**
     * 菜单标题
     */
    private String title;
    /**
     * 菜单级别
     */
    private Integer level;
    /**
     * 排序编号
     */
    private Integer sortNo;
    /**
     * 图标
     */
    private String icon;
    /**
     * menu、button
     */
    private String type;

}

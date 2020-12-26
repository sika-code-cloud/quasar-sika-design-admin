package com.quasar.sika.design.server.business.menu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sika.code.standard.base.pojo.entity.BaseStandardEntity;
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
 * @since 2020-12-26 16:59:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sika_menu")
public class MenuEntity extends BaseStandardEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 父菜单id
     */
    private Long parentId;

    /**
     * 菜单路径
     */
    private String path;

    /**
     * 菜单权限
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

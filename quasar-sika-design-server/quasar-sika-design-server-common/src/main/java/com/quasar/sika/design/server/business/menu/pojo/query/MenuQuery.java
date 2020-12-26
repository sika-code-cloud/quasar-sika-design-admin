package com.quasar.sika.design.server.business.menu.pojo.query;

import com.sika.code.standard.base.pojo.query.BaseStandardQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 菜单表 查询类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class MenuQuery extends BaseStandardQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据表id
     */
    protected Long menuId;
    /**
     * 父菜单id
     */
    protected Long parentId;
    /**
     * 菜单路径
     */
    protected String path;
    /**
     * 菜单权限
     */
    protected String resources;
    /**
     * 菜单标题
     */
    protected String title;
    /**
     * 菜单级别
     */
    protected Integer level;
    /**
     * 排序编号
     */
    protected Integer sortno;
    /**
     * 图标
     */
    protected String icon;
    /**
     * menu、button
     */
    protected String type;

}


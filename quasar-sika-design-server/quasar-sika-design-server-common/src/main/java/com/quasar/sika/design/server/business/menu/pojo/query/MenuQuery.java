package com.quasar.sika.design.server.business.menu.pojo.query;

import com.sika.code.standard.base.pojo.query.BaseStandardQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 菜单权限表 查询类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:03
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
     * 菜单名称
     */
    protected String menuName;
    /**
     * 父菜单ID
     */
    protected Long parentId;
    /**
     * 显示顺序
     */
    protected Integer orderNum;
    /**
     * 请求地址
     */
    protected String url;
    /**
     * 打开方式（menuItem页签 menuBlank新窗口）
     */
    protected String target;
    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    protected String menuType;
    /**
     * 菜单状态（0显示 1隐藏）
     */
    protected String visible;
    /**
     * 权限标识
     */
    protected String perms;
    /**
     * 菜单图标
     */
    protected String icon;
    /**
     * id列表
     */
    protected Set<Long> ids;

}


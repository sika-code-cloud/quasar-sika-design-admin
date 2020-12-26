package com.quasar.sika.design.server.business.role.pojo.query;

import com.sika.code.standard.base.pojo.query.BaseStandardQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 角色表 查询类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RoleQuery extends BaseStandardQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据表id
     */
    protected Long roleId;
    /**
     * 角色编码
     */
    protected String code;
    /**
     * 角色名称
     */
    protected String name;

    private Set<Long> roleIds;

}


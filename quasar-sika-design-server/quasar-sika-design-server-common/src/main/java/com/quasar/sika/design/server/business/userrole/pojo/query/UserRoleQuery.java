package com.quasar.sika.design.server.business.userrole.pojo.query;

import com.sika.code.standard.base.pojo.query.BaseStandardQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 用户角色表 查询类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:33
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserRoleQuery extends BaseStandardQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据表id
     */
    protected Long userRoleId;
    /**
     * 用户id
     */
    protected Long userId;
    /**
     * 角色id
     */
    protected Long roleId;

    private Set<Long> ids;
    private Set<Long> userIds;
    private Set<Long> roleIds;

}


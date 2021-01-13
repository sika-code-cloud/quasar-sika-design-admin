package com.quasar.sika.design.server.business.userrole.pojo.query;

import java.io.Serializable;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.sika.code.standard.base.pojo.query.BaseStandardQuery;

/**
 * <p>
 * 用户和角色关联表 查询类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-08 00:03:47
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
     * 用户ID
     */
    protected Long userId;
    /**
     * 角色ID
     */
    protected Long roleId;
    /**
     * id列表
     */
    protected Set<Long> ids;

}


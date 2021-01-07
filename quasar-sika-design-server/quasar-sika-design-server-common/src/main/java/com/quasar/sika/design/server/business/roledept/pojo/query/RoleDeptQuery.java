package com.quasar.sika.design.server.business.roledept.pojo.query;

import java.io.Serializable;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.sika.code.standard.base.pojo.query.BaseStandardQuery;

/**
 * <p>
 * 角色和部门关联表 查询类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-08 00:03:05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RoleDeptQuery extends BaseStandardQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据表id
     */
    protected Long roleDeptId;
    /**
     * 角色ID
     */
    protected Long roleId;
    /**
     * 部门ID
     */
    protected Long deptId;
    /**
     * id列表
     */
    protected Set<Long> ids;

}


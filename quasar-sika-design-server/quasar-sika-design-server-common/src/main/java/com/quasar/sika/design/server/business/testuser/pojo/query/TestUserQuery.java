package com.quasar.sika.design.server.business.testuser.pojo.query;

import com.sika.code.standard.base.pojo.query.BaseStandardQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 测试用户表 查询类
 * </p>
 *
 * @author daiqi
 * @since 2021-07-03 15:44:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TestUserQuery extends BaseStandardQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据表id
     */
    protected Long testUser0Id;
    /**
     * 用户id
     */
    protected Long userId;
    /**
     * 用户名称
     */
    protected String userName;
    /**
     * id列表
     */
    protected Set<Long> ids;

}


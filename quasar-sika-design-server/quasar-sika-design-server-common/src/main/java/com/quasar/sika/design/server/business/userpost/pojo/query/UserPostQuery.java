package com.quasar.sika.design.server.business.userpost.pojo.query;

import java.io.Serializable;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.sika.code.standard.base.pojo.query.BaseStandardQuery;

/**
 * <p>
 * 用户与岗位关联表 查询类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-08 00:03:37
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserPostQuery extends BaseStandardQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据表id
     */
    protected Long userPostId;
    /**
     * 用户ID
     */
    protected Long userId;
    /**
     * 岗位ID
     */
    protected Long postId;
    /**
     * id列表
     */
    protected Set<Long> ids;

}


package com.quasar.sika.design.server.business.post.pojo.query;

import com.sika.code.standard.base.pojo.query.BaseStandardQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 岗位信息表 查询类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:39
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PostQuery extends BaseStandardQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据表id
     */
    protected Long postId;
    /**
     * 岗位编码
     */
    protected String postCode;
    /**
     * 岗位名称
     */
    protected String postName;
    /**
     * 显示顺序
     */
    protected Integer postSort;
    /**
     * id列表
     */
    protected Set<Long> ids;

}


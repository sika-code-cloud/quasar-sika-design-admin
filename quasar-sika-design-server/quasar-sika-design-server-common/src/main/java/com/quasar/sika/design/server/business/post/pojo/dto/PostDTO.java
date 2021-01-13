package com.quasar.sika.design.server.business.post.pojo.dto;

import java.io.Serializable;
import com.sika.code.standard.base.pojo.dto.BaseStandardDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 岗位信息表
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:38
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PostDTO extends BaseStandardDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据表id
     */
    private Long postId;
    /**
     * 岗位编码
     */
    private String postCode;
    /**
     * 岗位名称
     */
    private String postName;
    /**
     * 显示顺序
     */
    private Integer postSort;

}

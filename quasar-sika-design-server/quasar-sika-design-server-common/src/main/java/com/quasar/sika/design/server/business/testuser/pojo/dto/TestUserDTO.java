package com.quasar.sika.design.server.business.testuser.pojo.dto;

import com.sika.code.standard.base.pojo.dto.BaseStandardDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 测试用户表
 * </p>
 *
 * @author daiqi
 * @since 2021-07-03 15:44:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TestUserDTO extends BaseStandardDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据表id
     */
    private Long testUser0Id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名称
     */
    private String userName;

}

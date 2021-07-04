package com.quasar.sika.design.server.business.testuser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sika.code.standard.base.pojo.entity.BaseStandardEntity;
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
 * @since 2021-07-03 15:44:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("test_user")
public class TestUserEntity extends BaseStandardEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;


}

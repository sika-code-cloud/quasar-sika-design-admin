package com.quasar.sika.design.server.business.testorder.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sika.code.standard.base.pojo.entity.BaseStandardEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 测试订单表
 * </p>
 *
 * @author daiqi
 * @since 2021-07-03 15:41:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("test_order")
public class TestOrderEntity extends BaseStandardEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 订单金额
     */
    private Long amount;

    /**
     * 订单日期
     */
    private Date orderDate;


}

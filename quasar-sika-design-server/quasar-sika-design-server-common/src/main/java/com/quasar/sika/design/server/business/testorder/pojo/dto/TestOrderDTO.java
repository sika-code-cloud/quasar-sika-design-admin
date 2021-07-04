package com.quasar.sika.design.server.business.testorder.pojo.dto;

import com.sika.code.standard.base.pojo.dto.BaseStandardDTO;
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
 * @since 2021-07-03 15:41:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TestOrderDTO extends BaseStandardDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据表id
     */
    private Long testOrderId;
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

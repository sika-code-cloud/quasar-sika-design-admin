package com.quasar.sika.design.server.business.testorder.pojo.query;

import com.sika.code.standard.base.pojo.query.BaseStandardQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * <p>
 * 测试订单表 查询类
 * </p>
 *
 * @author daiqi
 * @since 2021-07-03 15:41:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TestOrderQuery extends BaseStandardQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据表id
     */
    protected Long testOrderId;
    /**
     * 订单编号
     */
    protected String orderNo;
    /**
     * 订单金额
     */
    protected Long amount;
    /**
     * 订单日期
     */
    protected Date orderDate;
    /**
     * id列表
     */
    protected Set<Long> ids;

}


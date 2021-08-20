package com.quasar.sika.design.server.business.testorder.convert;

import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.quasar.sika.design.server.business.testorder.entity.TestOrderEntity;
import com.quasar.sika.design.server.business.testorder.pojo.dto.TestOrderDTO;

/**
 * <p>
 * 测试订单表 转换类
 * </p>
 *
 * @author daiqi
 * @since 2021-07-03 15:41:31
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TestOrderConvert extends BaseConvert<TestOrderEntity, TestOrderDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    TestOrderConvert INSTANCE = Mappers.getMapper(TestOrderConvert.class);

}

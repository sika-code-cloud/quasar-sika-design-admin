package com.quasar.sika.design.server.business.demo.convert;

import com.quasar.sika.design.server.business.demo.entity.DemoEntity;
import com.quasar.sika.design.server.business.demo.pojo.dto.DemoDTO;
import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 示例表 转换类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-23 00:51:13
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DemoConvert extends BaseConvert<DemoEntity, DemoDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    DemoConvert INSTANCE = Mappers.getMapper(DemoConvert.class);

}

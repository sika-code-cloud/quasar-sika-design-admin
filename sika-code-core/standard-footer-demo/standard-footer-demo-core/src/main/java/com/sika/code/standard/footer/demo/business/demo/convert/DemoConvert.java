package com.sika.code.standard.footer.demo.business.demo.convert;

import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.footer.demo.business.demo.entity.DemoEntity;
import com.sika.code.standard.footer.demo.business.demo.pojo.dto.DemoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 示例表 转换类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-22 12:18:45
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

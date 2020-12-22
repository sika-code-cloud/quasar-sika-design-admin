package com.quasar.sika.design.server.business.demo.convert;

import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.quasar.sika.design.server.business.demo.entity.DemoEntity;
import com.quasar.sika.design.server.business.demo.pojo.dto.DemoDTO;

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
    /**
     * <p>
     * 将实体对象转化为DTO对象
     * </p>
     *
     * @param demoEntity : 实体对象
     * @return demoDTO : 数据传输对象
     * @author daiqi
     * @date 2020-12-23 00:51:13
     */
    @Override
    @Mappings({@Mapping(source = "id", target = "demoId")})
    DemoDTO convertToDTO(DemoEntity demoEntity);

    /**
     * <p>
     * 将DTO对象转化为实体对象
     * </p>
     *
     * @param demoDTO : 数据传输对象
     * @return demoEntity : 实体对象
     * @author daiqi
     * @date 2020-12-23 00:51:13
     */
    @Override
    @Mappings({@Mapping(source = "demoId", target = "id")})
    DemoEntity convertToEntity(DemoDTO demoDTO);

}

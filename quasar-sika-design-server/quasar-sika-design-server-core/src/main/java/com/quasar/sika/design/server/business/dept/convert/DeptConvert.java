package com.quasar.sika.design.server.business.dept.convert;

import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.quasar.sika.design.server.business.dept.entity.DeptEntity;
import com.quasar.sika.design.server.business.dept.pojo.dto.DeptDTO;

/**
 * <p>
 * 部门表 转换类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:34:25
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptConvert extends BaseConvert<DeptEntity, DeptDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    DeptConvert INSTANCE = Mappers.getMapper(DeptConvert.class);

}

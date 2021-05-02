package com.quasar.sika.design.server.business.user.convert;

import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.quasar.sika.design.server.business.user.entity.UserEntity;
import com.quasar.sika.design.server.business.user.pojo.dto.UserDTO;

/**
 * <p>
 * 用户表 转换类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:13
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConvert extends BaseConvert<UserEntity, UserDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

}

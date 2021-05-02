package com.quasar.sika.design.server.business.userrole.convert;

import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.quasar.sika.design.server.business.userrole.entity.UserRoleEntity;
import com.quasar.sika.design.server.business.userrole.pojo.dto.UserRoleDTO;

/**
 * <p>
 * 用户和角色关联表 转换类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-08 00:03:54
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRoleConvert extends BaseConvert<UserRoleEntity, UserRoleDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    UserRoleConvert INSTANCE = Mappers.getMapper(UserRoleConvert.class);

}

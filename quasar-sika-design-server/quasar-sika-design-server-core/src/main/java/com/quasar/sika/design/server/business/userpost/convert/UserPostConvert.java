package com.quasar.sika.design.server.business.userpost.convert;

import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.quasar.sika.design.server.business.userpost.entity.UserPostEntity;
import com.quasar.sika.design.server.business.userpost.pojo.dto.UserPostDTO;

/**
 * <p>
 * 用户与岗位关联表 转换类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-08 00:03:44
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserPostConvert extends BaseConvert<UserPostEntity, UserPostDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    UserPostConvert INSTANCE = Mappers.getMapper(UserPostConvert.class);

}

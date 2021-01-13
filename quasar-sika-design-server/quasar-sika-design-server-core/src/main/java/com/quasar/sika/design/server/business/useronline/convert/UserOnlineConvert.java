package com.quasar.sika.design.server.business.useronline.convert;

import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.quasar.sika.design.server.business.useronline.entity.UserOnlineEntity;
import com.quasar.sika.design.server.business.useronline.pojo.dto.UserOnlineDTO;

/**
 * <p>
 * 在线用户记录 转换类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:36:32
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserOnlineConvert extends BaseConvert<UserOnlineEntity, UserOnlineDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    UserOnlineConvert INSTANCE = Mappers.getMapper(UserOnlineConvert.class);

}

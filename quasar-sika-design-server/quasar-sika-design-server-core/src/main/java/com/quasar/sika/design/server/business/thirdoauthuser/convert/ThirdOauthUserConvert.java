package com.quasar.sika.design.server.business.thirdoauthuser.convert;

import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.quasar.sika.design.server.business.thirdoauthuser.entity.ThirdOauthUserEntity;
import com.quasar.sika.design.server.business.thirdoauthuser.pojo.dto.ThirdOauthUserDTO;

/**
 * <p>
 * 第三方授权用户表 转换类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-01 23:24:34
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ThirdOauthUserConvert extends BaseConvert<ThirdOauthUserEntity, ThirdOauthUserDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    ThirdOauthUserConvert INSTANCE = Mappers.getMapper(ThirdOauthUserConvert.class);

}

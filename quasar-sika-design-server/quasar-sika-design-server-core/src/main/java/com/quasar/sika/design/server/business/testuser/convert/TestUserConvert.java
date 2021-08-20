package com.quasar.sika.design.server.business.testuser.convert;

import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.quasar.sika.design.server.business.testuser.entity.TestUserEntity;
import com.quasar.sika.design.server.business.testuser.pojo.dto.TestUserDTO;

/**
 * <p>
 * 测试用户表 转换类
 * </p>
 *
 * @author daiqi
 * @since 2021-07-03 15:44:06
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TestUserConvert extends BaseConvert<TestUserEntity, TestUserDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    TestUserConvert INSTANCE = Mappers.getMapper(TestUserConvert.class);

}

package com.quasar.sika.design.server.business.user.mapper;

import com.quasar.sika.design.server.business.user.entity.UserEntity;
import com.sika.code.standard.base.basemapper.BaseStandardMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:10
 */
@Repository
public interface UserMapper extends BaseStandardMapper<UserEntity> {

}

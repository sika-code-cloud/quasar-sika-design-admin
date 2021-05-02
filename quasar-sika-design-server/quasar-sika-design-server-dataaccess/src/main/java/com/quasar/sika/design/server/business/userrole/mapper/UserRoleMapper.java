package com.quasar.sika.design.server.business.userrole.mapper;

import com.quasar.sika.design.server.business.userrole.entity.UserRoleEntity;
import org.springframework.stereotype.Repository;
import com.sika.code.standard.base.basemapper.BaseStandardMapper;

/**
 * <p>
 * 用户和角色关联表 Mapper 接口
 * </p>
 *
 * @author daiqi
 * @since 2021-01-08 00:03:51
 */
@Repository
public interface UserRoleMapper extends BaseStandardMapper<UserRoleEntity> {

}

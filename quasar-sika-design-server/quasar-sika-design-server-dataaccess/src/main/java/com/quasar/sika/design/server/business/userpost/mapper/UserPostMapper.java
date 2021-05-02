package com.quasar.sika.design.server.business.userpost.mapper;

import com.quasar.sika.design.server.business.userpost.entity.UserPostEntity;
import org.springframework.stereotype.Repository;
import com.sika.code.standard.base.basemapper.BaseStandardMapper;

/**
 * <p>
 * 用户与岗位关联表 Mapper 接口
 * </p>
 *
 * @author daiqi
 * @since 2021-01-08 00:03:41
 */
@Repository
public interface UserPostMapper extends BaseStandardMapper<UserPostEntity> {

}

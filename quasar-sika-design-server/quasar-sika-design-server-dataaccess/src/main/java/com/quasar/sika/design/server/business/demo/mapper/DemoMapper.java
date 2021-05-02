package com.quasar.sika.design.server.business.demo.mapper;

import com.quasar.sika.design.server.business.demo.entity.DemoEntity;
import org.springframework.stereotype.Repository;
import com.sika.code.standard.base.basemapper.BaseStandardMapper;

/**
 * <p>
 * 示例表 Mapper 接口
 * </p>
 *
 * @author daiqi
 * @since 2020-12-22 23:55:45
 */
@Repository
public interface DemoMapper extends BaseStandardMapper<DemoEntity> {
}

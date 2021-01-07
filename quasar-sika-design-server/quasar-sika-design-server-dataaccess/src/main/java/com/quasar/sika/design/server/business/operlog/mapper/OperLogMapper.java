package com.quasar.sika.design.server.business.operlog.mapper;

import com.quasar.sika.design.server.business.operlog.entity.OperLogEntity;
import org.springframework.stereotype.Repository;
import com.sika.code.standard.base.basemapper.BaseStandardMapper;

/**
 * <p>
 * 操作日志记录 Mapper 接口
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:32
 */
@Repository
public interface OperLogMapper extends BaseStandardMapper<OperLogEntity> {

}

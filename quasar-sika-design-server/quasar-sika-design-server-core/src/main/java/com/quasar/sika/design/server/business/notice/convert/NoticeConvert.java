package com.quasar.sika.design.server.business.notice.convert;

import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.quasar.sika.design.server.business.notice.entity.NoticeEntity;
import com.quasar.sika.design.server.business.notice.pojo.dto.NoticeDTO;

/**
 * <p>
 * 通知公告表 转换类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:24
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NoticeConvert extends BaseConvert<NoticeEntity, NoticeDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    NoticeConvert INSTANCE = Mappers.getMapper(NoticeConvert.class);

}

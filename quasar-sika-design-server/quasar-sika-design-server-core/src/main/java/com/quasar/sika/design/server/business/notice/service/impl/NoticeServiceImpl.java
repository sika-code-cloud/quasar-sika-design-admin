package com.quasar.sika.design.server.business.notice.service.impl;

import com.quasar.sika.design.server.business.notice.entity.NoticeEntity;
import com.quasar.sika.design.server.business.notice.mapper.NoticeMapper;
import com.quasar.sika.design.server.business.notice.pojo.dto.NoticeDTO;
import com.quasar.sika.design.server.business.notice.service.NoticeService;
import com.quasar.sika.design.server.business.notice.convert.NoticeConvert;

import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通知公告表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:22
 */
@Service(value = "noticeService")
public class NoticeServiceImpl extends BaseStandardServiceImpl<NoticeMapper, NoticeEntity, NoticeDTO> implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;


    @Override
    protected BaseConvert<NoticeEntity, NoticeDTO> convert() {
        return NoticeConvert.INSTANCE;
    }
}


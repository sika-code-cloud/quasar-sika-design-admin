package com.quasar.sika.design.server.business.post.service.impl;

import com.quasar.sika.design.server.business.post.entity.PostEntity;
import com.quasar.sika.design.server.business.post.mapper.PostMapper;
import com.quasar.sika.design.server.business.post.pojo.dto.PostDTO;
import com.quasar.sika.design.server.business.post.service.PostService;
import com.quasar.sika.design.server.business.post.convert.PostConvert;

import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 岗位信息表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:46
 */
@Service(value = "postService")
public class PostServiceImpl extends BaseStandardServiceImpl<PostMapper, PostEntity, PostDTO> implements PostService {
    @Autowired
    private PostMapper postMapper;


    @Override
    protected BaseConvert<PostEntity, PostDTO> convert() {
        return PostConvert.INSTANCE;
    }
}


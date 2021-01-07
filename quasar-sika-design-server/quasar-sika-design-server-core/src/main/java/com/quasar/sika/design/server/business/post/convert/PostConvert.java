package com.quasar.sika.design.server.business.post.convert;

import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.quasar.sika.design.server.business.post.entity.PostEntity;
import com.quasar.sika.design.server.business.post.pojo.dto.PostDTO;

/**
 * <p>
 * 岗位信息表 转换类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:47
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostConvert extends BaseConvert<PostEntity, PostDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    PostConvert INSTANCE = Mappers.getMapper(PostConvert.class);

}

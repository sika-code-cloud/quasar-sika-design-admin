package com.quasar.sika.design.server.business.thirdoauthuser.service;

import com.quasar.sika.design.server.business.thirdoauthuser.pojo.dto.ThirdOauthUserDTO;
import com.sika.code.standard.base.service.BaseStandardService;
import me.zhyd.oauth.model.AuthUser;

/**
 * <p>
 * 第三方授权用户表 服务类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-01 23:24:33
 */
public interface ThirdOauthUserService extends BaseStandardService<ThirdOauthUserDTO> {
    /**
     * 根据uuid和source查询授权数据
     *
     * @param uuid
     * @param source
     * @return
     */
    ThirdOauthUserDTO findByUuidAndSource(String uuid, String source);

    /**
     * <p>
     * 根据state和source查询授权数据
     * </p>
     * @author daiqi
     * @date * 11:50
     * @param state
     * @param source
     * @return com.quasar.sika.design.server.business.thirdoauthuser.pojo.dto.ThirdOauthUserDTO
     */
    ThirdOauthUserDTO findByStateAndSource(String state, String source);

    /**
     * 根据第三方授权用户 新增|修改该用户的授权信息
     * 更新索引：uuid + source
     *
     * @param authUser
     * @return
     */
    ThirdOauthUserDTO modifyByAuthUser(AuthUser authUser, String state);
}

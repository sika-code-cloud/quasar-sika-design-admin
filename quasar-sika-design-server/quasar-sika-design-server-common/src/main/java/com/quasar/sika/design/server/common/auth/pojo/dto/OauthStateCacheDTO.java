package com.quasar.sika.design.server.common.auth.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 第三方授权state对应缓存类
 *
 * @author daiqi
 * @create 2021-01-12 0:40
 */
@Data
@Accessors(chain = true)
public class OauthStateCacheDTO {
    /**
     * 跳转前客户端待回调的客户端url
     */
    private String clientUrl;
    /**
     * 客户端请求授权登录url重定向时的sessionId
     */
    private String clientSessionId;
}

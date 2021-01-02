package com.quasar.sika.design.server.common.auth.pojo.request;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 绑定授权用户请求类
 */
@Data
@Accessors(chain = true)
public class BindOauthUserRequest {
    /** 邮箱 */
    private String email;
    /** 客户端授权码 */
    private String clientOauthCode;
}

package com.quasar.sika.design.server.common.auth.properties;

import lombok.*;
import me.zhyd.oauth.config.AuthConfig;

/**
 * @author daiqi
 * @create 2020-12-30 14:36
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthConfigExp extends AuthConfig {
    private String requestClassName;
}

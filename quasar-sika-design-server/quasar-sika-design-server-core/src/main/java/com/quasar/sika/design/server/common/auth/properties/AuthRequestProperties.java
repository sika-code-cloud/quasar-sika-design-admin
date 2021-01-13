package com.quasar.sika.design.server.common.auth.properties;

import com.google.common.collect.Maps;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author daiqi
 * @create 2020-12-30 13:28
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Order(-1)
@Slf4j
@Component
@ConfigurationProperties(prefix = "me.zhyd.oauth")
public class AuthRequestProperties {
    private Map<String, AuthConfigExp> oauthConfig = Maps.newHashMap();
}

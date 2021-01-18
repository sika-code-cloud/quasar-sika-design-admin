package com.quasar.sika.design.server.common.auth.properties;

import com.google.common.collect.Maps;
import com.xkcoding.http.config.HttpConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
    private int timeout = 3000;
    private Map<String, AuthConfigExp> oauthConfig = Maps.newHashMap();

    @PostConstruct
    public void init() {
        for (AuthConfigExp authConfigExp : oauthConfig.values()) {
            if (authConfigExp.getHttpConfig() == null) {
                authConfigExp.setHttpConfig(HttpConfig.builder().timeout(timeout).build());
            }
        }
    }
}

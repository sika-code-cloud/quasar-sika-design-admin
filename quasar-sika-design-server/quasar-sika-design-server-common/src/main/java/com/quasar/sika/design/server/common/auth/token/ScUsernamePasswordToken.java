package com.quasar.sika.design.server.common.auth.token;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author sikadai
 * @Description: 自定义的用户名密码Token
 * @date 2021/1/1620:32
 */
@Data
@Accessors(chain = true)
public class ScUsernamePasswordToken extends UsernamePasswordToken {
    /**
     * 是否是加密密码
     */
    private boolean encryptedPassword;


    public ScUsernamePasswordToken() {
    }

    public void setPassword(String password) {
        if (StrUtil.isNotBlank(password)) {
            super.setPassword(password.toCharArray());
        }
    }

    public ScUsernamePasswordToken(String username, String password) {
        super(username, password);
    }

    public ScUsernamePasswordToken(String username, String password, String host) {
        super(username, password, host);
    }

    public ScUsernamePasswordToken(String username, String password, boolean rememberMe) {
        super(username, password, rememberMe);
    }

    public ScUsernamePasswordToken(String username, String password, boolean rememberMe, String host) {
        super(username, password, rememberMe, host);
    }
}

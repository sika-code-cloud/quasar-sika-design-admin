package com.quasar.sika.design.server.business.user.pojo.dto;

import com.sika.code.standard.base.pojo.dto.BaseStandardDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import me.zhyd.oauth.model.AuthUser;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserDTO extends BaseStandardDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据表id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 授权密码
     */
    private String oauthPwd;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 性别【1：男，2：女，0：未知】
     */
    private Integer sex;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String avatar;
    /**
     * token
     */
    private String token;
    /**
     * 用户类型：1：游客，2：系统用户
     */
    private Integer type;
    private AuthUser authUser;

    public UserDTO build(AuthUser authUser) {
        this.authUser = authUser;
        return this;
    }

}

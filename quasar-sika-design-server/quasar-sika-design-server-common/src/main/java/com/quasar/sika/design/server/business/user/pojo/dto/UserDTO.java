package com.quasar.sika.design.server.business.user.pojo.dto;

import com.quasar.sika.design.server.business.thirdoauthuser.pojo.dto.ThirdOauthUserDTO;
import com.sika.code.standard.base.pojo.dto.BaseStandardDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
     * 省份编码
     */
    private String provinceCode;
    /**
     * 城市编码
     */
    private String cityCode;
    /**
     * 乡编码
     */
    private String countyCode;
    /**
     * token
     */
    private String token;
    /**
     * 用户类型：1：游客，2：系统用户
     */
    private Integer type;
    /**
     * 地址
     */
    private String address;

    private ThirdOauthUserDTO oauthUser;

    public String getShowUsername() {
        if (this.oauthUser != null) {
            return this.oauthUser.getUsername();
        }
        return this.username;
    }

}

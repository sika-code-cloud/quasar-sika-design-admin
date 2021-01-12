package com.quasar.sika.design.server.business.thirdoauthuser.entity;

import java.io.Serializable;

import com.sika.code.standard.base.pojo.entity.BaseStandardEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 第三方授权用户表
 * </p>
 *
 * @author daiqi
 * @since 2021-01-01 23:24:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sika_third_oauth_user")
public class ThirdOauthUserEntity extends BaseStandardEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 第三方uuid
     */
    private String uuid;

    /**
     * 第三方用户名称
     */
    private String username;

    /**
     * 第三方昵称
     */
    private String nickname;

    /**
     * 授权登录后的state
     */
    private String state;

    /**
     * 头像链接
     */
    private String avatar;

    /**
     * 博客
     */
    private String blog;

    /**
     * 公司或者组织
     */
    private String company;

    /**
     * 所在地点
     */
    private String location;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别
     */
    private String gender;

    /**
     * 来源【gitee|gethub】等等
     */
    private String source;

    /**
     * token
     */
    private String token;

    /**
     * rawUserInfo
     */
    private String rawUserInfo;

    /**
     * 关联用户id【为0表示没有关联】
     */
    private Long userId;

    public String getState() {
        if (state != null) {
            return state.toUpperCase();
        }
        return null;
    }

}

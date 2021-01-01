package com.quasar.sika.design.server.business.thirdoauthuser.pojo.query;

import com.sika.code.standard.base.pojo.query.BaseStandardQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 第三方授权用户表 查询类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-01 23:24:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ThirdOauthUserQuery extends BaseStandardQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据表id
     */
    protected Long thirdOauthUserId;
    /**
     * 第三方uuid
     */
    protected String uuid;
    /**
     * 第三方用户名称
     */
    protected String username;
    /**
     * 第三方昵称
     */
    protected String nickname;
    /**
     * 头像链接
     */
    protected String avatar;
    /**
     * 博客
     */
    protected String blog;
    /**
     * 公司或者组织
     */
    protected String company;
    /**
     * 所在地点
     */
    protected String location;
    /**
     * 邮箱
     */
    protected String email;
    /**
     * 性别
     */
    protected String gender;
    /**
     * 来源【gitee|gethub】等等
     */
    protected String source;
    /**
     * token
     */
    protected String token;
    /**
     * rawUserInfo
     */
    protected String rawUserInfo;
    /**
     * 关联用户id【为0表示没有关联】
     */
    protected Long userId;
    /**
     * id列表
     */
    protected Set<Long> ids;

}


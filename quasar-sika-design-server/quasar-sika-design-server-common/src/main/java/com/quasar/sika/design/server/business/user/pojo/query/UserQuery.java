package com.quasar.sika.design.server.business.user.pojo.query;

import com.sika.code.standard.base.pojo.query.BaseStandardQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 用户表 查询类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 20:15:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserQuery extends BaseStandardQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据表id
     */
    protected Long userId;
    /**
     * 用户名
     */
    protected String username;
    /**
     * 密码
     */
    protected String password;
    /**
     * 授权密码
     */
    protected String oauthPwd;
    /**
     * 用户昵称
     */
    protected String nickname;
    /**
     * 性别【1：男，2：女，0：未知】
     */
    protected Integer sex;
    /**
     * 手机号
     */
    protected String phone;
    /**
     * 邮箱
     */
    protected String email;
    /**
     * 头像
     */
    protected String avatar;
    /**
     * token
     */
    protected String token;
    /**
     * 用户类型：1：游客，2：系统用户
     */
    protected Integer type;
    /**
     * id列表
     */
    protected Set<Long> ids;

}


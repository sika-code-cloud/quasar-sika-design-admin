package com.quasar.sika.design.server.common.shiro.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sika.code.database.common.entity.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <p>  系统管理-用户基础信息表 </p>
 *
 * @author: zhengqing
 * @date: 2019-08-19
 */
@Data
@TableName("t_sys_user")
// 对注解分组的排序，可以通脱他判断先后顺序
//@GroupSequence({FieldRepeatValidator.class,NotNull.class, Default.class})
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID  groups:标识在更新的时候才能验证非空
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 账号
     */
    @TableField("username")
    @Length(max = 100, message = "账号不能超过100个字符")
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9\\*]*$", message = "账号限制：最多100字符，包含文字、字母和数字")
    private String username;
    /**
     * 登录密码
     */
    @TableField("password")
    private String password;
    /**
     * 明文密码 - QQ第三方授权登录时用
     */
    @TableField("pwd")
    @NotBlank(message = "密码不能为空")
//	@FieldRepeatValidator(className = "com.zhengqing.modules.system.entity.User", field = "pwd", message = "密码重复！")
//	@FieldRepeatValidator(className = "com.zhengqing.modules.system.entity.User", field = "pwd", message = "密码重复！",groups={FieldRepeatValidator.class})
    private String pwd;
    /**
     * 昵称
     */
    @TableField("nick_name")
    @NotBlank(message = "昵称不能为空")
    private String nickName;
    /**
     * 性别 0:男 1:女
     */
    @TableField("sex")
    private String sex;
    /**
     * 手机号码
     */
    @TableField("phone")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String phone;
    /**
     * 邮箱
     */
    @TableField("email")
    @NotBlank(message = "联系邮箱不能为空")
    @Email(message = "邮箱格式不对")
    private String email;
    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;
    /**
     * 状态
     */
    @TableField("flag")
    private String flag;
    /**
     * 盐值
     */
    @TableField("salt")
    private String salt;
    /**
     * token
     */
    @TableField("token")
    private String token;

    @TableField("qq_oppen_id")
    private String qqOppenId;

//	@NotBlank(message = "身份证号不能为空")
//	@IdentityCardNumber(message = "身份证信息有误,请核对后提交")
//	private String clientCardNo;


}

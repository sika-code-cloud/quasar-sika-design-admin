package com.quasar.sika.design.server.common.auth.pojo.request;

import com.quasar.sika.design.server.business.user.pojo.dto.UserDTO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 认证注册请求类
 * </p>
 *
 * @author daiqi
 * @date 2021/1/9 23:27
 */
@Data
@Accessors(chain = true)
public class AuthRegisterRequest extends UserDTO {
}

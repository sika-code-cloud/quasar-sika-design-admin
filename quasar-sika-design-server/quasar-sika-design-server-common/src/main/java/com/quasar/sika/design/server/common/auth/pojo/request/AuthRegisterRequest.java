package com.quasar.sika.design.server.common.auth.pojo.request;

import com.quasar.sika.design.server.business.user.pojo.dto.UserDTO;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthRegisterRequest extends UserDTO {

}

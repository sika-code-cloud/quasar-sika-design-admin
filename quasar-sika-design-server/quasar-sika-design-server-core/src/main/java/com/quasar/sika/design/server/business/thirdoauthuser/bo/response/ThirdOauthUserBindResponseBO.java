package com.quasar.sika.design.server.business.thirdoauthuser.bo.response;

import com.quasar.sika.design.server.business.user.pojo.dto.UserDTO;
import com.sika.code.standard.base.pojo.bo.response.BaseStandardResponseBO;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ThirdOauthUserBindResponseBO implements BaseStandardResponseBO<UserDTO> {
    private UserDTO userDTO;

    @Override
    public void build(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}

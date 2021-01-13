package com.quasar.sika.design.server.business.thirdoauthuser.bo.response;

import com.quasar.sika.design.server.business.thirdoauthuser.pojo.dto.ThirdOauthUserDTO;
import com.sika.code.standard.base.pojo.bo.response.BaseStandardResponseBO;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ThirdOauthUserModifyResponseBO implements BaseStandardResponseBO<ThirdOauthUserDTO> {
    private ThirdOauthUserDTO oauthUserDTO;
    @Override
    public void build(ThirdOauthUserDTO thirdOauthUserDTO) {
        this.oauthUserDTO = thirdOauthUserDTO;
    }
}

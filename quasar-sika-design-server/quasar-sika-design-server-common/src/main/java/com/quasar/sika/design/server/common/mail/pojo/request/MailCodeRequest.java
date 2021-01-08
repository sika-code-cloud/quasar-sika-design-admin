package com.quasar.sika.design.server.common.mail.pojo.request;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sikadai
 * @Description:
 * @date 2021/1/222:51
 */
@Data
@Accessors(chain = true)
public class MailCodeRequest {
    private Integer type;
    private String code;
}

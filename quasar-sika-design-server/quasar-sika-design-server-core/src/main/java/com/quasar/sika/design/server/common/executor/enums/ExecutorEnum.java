package com.quasar.sika.design.server.common.executor.enums;

import com.quasar.sika.design.server.common.mail.executor.CheckMailCodeExecutor;
import com.quasar.sika.design.server.common.mail.executor.SendMailCodeExecutor;
import com.sika.code.standard.base.constant.StandardExecutorEnumInf;
import com.sika.code.standard.base.pojo.executor.BaseStandardExecutor;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sikadai
 * @Description:
 * @date 2021/1/217:05
 */
@Getter
@AllArgsConstructor
public enum ExecutorEnum implements StandardExecutorEnumInf {
    /** 邮箱校验执行者 */
    CHECK_MAIL("_CHECK_MAIL_000001", CheckMailCodeExecutor.class, 2, "邮箱校验执行者"),
    SEND_MAIL("_CHECK_MAIL_000002", SendMailCodeExecutor.class, 2, "邮箱校验执行者");
    private final String code;
    private final Class<? extends BaseStandardExecutor> executorClass;
    private final Integer type;
    private final String desc;

    @Override
    public String getCode() {
        return type + code;
    }
}
package com.quasar.sika.design.server.common.mail.executor;

import cn.hutool.core.util.StrUtil;
import com.quasar.sika.design.server.common.mail.constant.MailCodeEnum;
import com.quasar.sika.design.server.common.mail.context.CheckMailCodeContext;
import com.quasar.sika.design.server.common.mail.domain.MailDomain;
import com.quasar.sika.design.server.common.mail.pojo.request.CheckMailRequest;
import com.sika.code.basic.constant.TypeEnumInf;
import com.sika.code.basic.pojo.dto.ServiceResult;
import com.sika.code.basic.util.Assert;
import com.sika.code.exception.BusinessException;
import com.sika.code.standard.base.pojo.executor.BaseStandardExecutor;

/**
 * @author sikadai
 * @Description:认证登录执行者
 * @date 2021/4/423:20
 */
public class CheckMailCodeExecutor extends BaseStandardExecutor<CheckMailCodeContext> implements MailDomain {

    @Override
    protected void executeBefore() {
        CheckMailRequest request = context.getRequest();
        Assert.verifyObjNull(request, "邮箱请求对象");
        Assert.verifyStrEmpty(request.getClientMailCode(), "客户端验证码");

        MailCodeEnum mailCodeEnum = TypeEnumInf.findByCode(request.getCode(), MailCodeEnum.class);
        Assert.verifyObjNullMsg(mailCodeEnum, "邮件验证码类型有误");
        request.setType(mailCodeEnum.getType());
        request.setCode(mailCodeEnum.getCode());

    }

    @Override
    protected ServiceResult doExecute() {
        CheckMailRequest request = context.getRequest();
        String mailCode = mailService().getMailCode(request);
        if (StrUtil.isBlank(mailCode)) {
            throw new BusinessException("邮箱验证码已失效");
        }
        if (!StrUtil.equalsIgnoreCase(mailCode, request.getClientMailCode())) {
            throw new BusinessException("邮箱验证码有误");
        }
        return ServiceResult.newInstanceOfSuccess();
    }

}

package com.quasar.sika.design.server.common.mail.bo.request.checker;

import cn.hutool.core.util.StrUtil;
import com.quasar.sika.design.server.common.mail.bo.response.CheckMailCodeResponseBO;
import com.quasar.sika.design.server.common.mail.constant.MailCodeEnum;
import com.quasar.sika.design.server.common.mail.domain.MailDomain;
import com.quasar.sika.design.server.common.mail.pojo.request.CheckMailRequest;
import com.quasar.sika.design.server.common.mail.pojo.response.CheckMailResponse;
import com.sika.code.basic.constant.TypeEnumInf;
import com.sika.code.basic.util.Assert;
import com.sika.code.exception.BusinessException;
import com.sika.code.standard.base.pojo.bo.request.BaseStandardRequestBO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sikadai
 * @Description:
 * @date 2021/1/31:30
 */
@Data
@Accessors(chain = true)
public class CheckMailCodeRequestBO extends BaseStandardRequestBO<CheckMailCodeResponseBO> implements MailDomain {
    protected CheckMailRequest request;

    @Override
    protected void init() {
        buildRequest();
    }

    protected void buildRequest() {
        MailCodeEnum mailCodeEnum = TypeEnumInf.findByCode(request.getCode(), MailCodeEnum.class);
        Assert.verifyObjNullMsg(mailCodeEnum, "邮件验证码类型有误");
        request.setType(mailCodeEnum.getType());
        request.setCode(mailCodeEnum.getCode());
    }

    @Override
    protected void verify() {
        Assert.verifyObjNull(request, "邮箱请求对象");
        Assert.verifyStrEmpty(request.getClientMailCode(), "客户端验证码");
    }

    @Override
    protected CheckMailCodeResponseBO doExecute() {
        // 1: 校验邮箱验证码
        checkCode();
        return newResponseBO(new CheckMailResponse());
    }

    @Override
    public Class<CheckMailCodeResponseBO> responseClass() {
        return CheckMailCodeResponseBO.class;
    }

    /**
     * 校验验证码 -- 不删除缓存
     */
    protected void checkCode() {
        String mailCode = mailService().getMailCode(request);
        if (StrUtil.isBlank(mailCode)) {
            throw new BusinessException("邮箱验证码已失效");
        }
        if (!StrUtil.equalsIgnoreCase(mailCode, request.getClientMailCode())) {
            throw new BusinessException("邮箱验证码有误");
        }
    }

    /**
     * 校验验证码 --- 通过后删除
     */
    protected void checkCodeAndRemove() {
        checkCode();
        // code码移除缓存
        removeCode();
    }

    public void removeCode() {
        // code码移除缓存
        boolean bool = mailService().removeMailCode(request);
        if (!bool) {
            throw new BusinessException("系统异常");
        }
    }

}

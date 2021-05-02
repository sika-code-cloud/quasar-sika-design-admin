package com.sika.code.exception;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.sika.code.basic.errorcode.BaseErrorCode;
import com.sika.code.basic.errorcode.BaseErrorCodeEnum;
import com.sika.code.basic.pojo.dto.ServiceResult;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.array.ArrayUtil;
import com.sika.code.common.json.util.JSONUtil;
import com.sika.code.informer.BaseInformer;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 业务校验异常
 *
 * @author daiqi
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends RuntimeException {

    protected String code;
    protected String message;
    /**
     * 异常详情
     */
    private String messageDetail;
    /**
     * 异常详情信息
     */
    private Object exceptionDetail;
    /**
     * 封装的数据
     */
    private Object data;
    /**
     * 异常通知者
     */
    private BaseInformer informer;

    private List<BaseInformer> informers;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        this.code = BaseErrorCodeEnum.BUSINESS_EXCEPTION.getCode();
        this.message = message;
    }
    public BusinessException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BusinessException(BaseErrorCode e) {
        this(e.getCode(), e.getDesc());
    }

    public BusinessException(BaseErrorCode baseErrorCode, String message) {
        this(baseErrorCode.getCode(), message);
    }

    public BusinessException(String errorCode, String errorMsg, Object... formatValues) {
        setCode(errorCode).setMessage(errorMsg);
        this.buildFormatValues(formatValues);
    }

    public BusinessException buildExceptionDetail(Object exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
        return this;
    }

    /**
     * 构建需要替换msg中参数的值
     */
    public BusinessException buildFormatValues(Object... formatValues) {
        if (ArrayUtil.isEmpty(formatValues)) {
            formatValues = new String[]{"数据"};
        }
        this.message = StrUtil.format(this.message, formatValues);
        return this;
    }

    public BusinessException buildExceptionInformer(BaseInformer exceptionInformer) {
        this.informer = exceptionInformer;
        addInformer(exceptionInformer);
        return this;
    }


    public String getMessageDetail() {
        if (BaseUtil.isNotNull(this.messageDetail)) {
            return this.messageDetail;
        }
        ServiceResult result = ServiceResult.newInstance();
        result.buildCodeAndMessage(getCode(), getMessage());
        result.buildResult(exceptionDetail);
        this.messageDetail = JSONUtil.toJSONString(result);
        return this.messageDetail;
    }

    public BusinessException addInformer(BaseInformer informer) {
        if (BaseUtil.isNull(informer)) {
            return this;
        }
        if (BaseUtil.isNull(this.informers)) {
            this.informers = Lists.newArrayList();
        }
        this.informers.add(informer);
        return this;
    }

    public BusinessException addInformers(List<BaseInformer> informers) {
        if (CollUtil.isEmpty(informers)) {
            return this;
        }
        if (BaseUtil.isNull(this.informers)) {
            this.informers = Lists.newArrayList();
        }
        this.informers.addAll(informers);
        return this;
    }
}

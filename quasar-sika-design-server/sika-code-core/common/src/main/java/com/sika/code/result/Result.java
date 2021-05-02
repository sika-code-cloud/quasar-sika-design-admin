package com.sika.code.result;


import com.sika.code.basic.errorcode.BaseErrorCode;
import com.sika.code.basic.errorcode.BaseErrorCodeEnum;
import com.sika.code.basic.pojo.dto.BaseMsgDTO;
import com.sika.code.basic.pojo.dto.ServiceResult;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 封装统一的返回结果
 * </p>
 *
 * @author daiqi
 * @date 2019/5/24 23:48
 */
@Data
@Accessors(chain = true)
public class Result extends BaseMsgDTO implements Serializable {

    private static final long serialVersionUID = 1998428067166208629L;

    public Result() {

    }

    private Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private Result(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    private Result(Object data) {
        super(data);
    }

    private Result(Object data, BaseErrorCode errorCode) {
        super(data, errorCode);
    }

    /**
     * <p>
     * 若匹配的是Object对象则默认是成功的
     * </p>
     *
     * @param data : 需要封装的数据
     * @return Result
     * @author daiqi
     * @date 2019/5/9 20:39
     */
    public static Result newInstance(Object data) {
        if (data instanceof ServiceResult) {
            ServiceResult<?> result = (ServiceResult<?>) data;
            if (result.getSuccess()) {
                return new Result(result.getResult());
            } else {
                return new Result(result.getCode(), result.getMessage(), result.getResult());
            }
        }
        return new Result(data);
    }

    public static Result newError(String message) {
        Result result = new Result();
        result.setCode(BaseErrorCodeEnum.SYS_ERROR.getCode());
        result.setMessage(message);
        result.setSuccess(false);
        return result;
    }

}
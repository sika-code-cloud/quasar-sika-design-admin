package com.sika.code.standard.base.executor;

import com.sika.code.basic.pojo.request.BaseRequestBO;
import com.sika.code.basic.pojo.response.BaseResponseBO;
import com.sika.code.basic.util.Assert;

/**
 * 领域渠道模式的执行器
 *
 * @author daiqi
 * @create 2021-01-04 15:43
 */
public class DomainExecutor {
    /**
     * <p>
     * 传入请求的业务逻辑对象，执行执行操作，返回执行后的responseBO
     * </p>
     *
     * @param requestBO
     * @return Response
     * @author daiqi
     * @date 2021/1/4 15:45
     */
    public static <POJO, Response extends BaseResponseBO<POJO>> Response execute(BaseRequestBO<Response> requestBO) {
        Assert.verifyObjNull(requestBO, "业务逻辑请求对象");
        return requestBO.execute();
    }
}

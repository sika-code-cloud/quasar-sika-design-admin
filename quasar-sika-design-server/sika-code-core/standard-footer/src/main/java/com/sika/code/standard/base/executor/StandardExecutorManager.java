package com.sika.code.standard.base.executor;

import cn.hutool.core.util.StrUtil;
import com.sika.code.basic.pojo.dto.ServiceResult;
import com.sika.code.basic.pojo.request.BaseRequestBO;
import com.sika.code.basic.pojo.response.BaseResponseBO;
import com.sika.code.basic.util.Assert;
import com.sika.code.standard.base.pojo.context.BaseStandardContext;
import com.sika.code.standard.base.pojo.executor.BaseStandardExecutor;

/**
 * 领域渠道模式的执行器
 *
 * @author daiqi
 * @create 2021-01-04 15:43
 */
public class StandardExecutorManager {
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

    public ServiceResult execute(BaseStandardContext context) {
        String executorKey = context.getExecutorKey();
        Assert.verifyStrEmptyMsg(executorKey, "执行者key为空");
        BaseStandardExecutor executor = getExecutor(executorKey);
        Assert.verifyObjNullMsg(executor, StrUtil.format("执行者KEY【{}】对应的执行者对象不存在", executorKey));
        return executor.execute(context);
    }

    protected BaseStandardExecutor getExecutor(String executorKey) {
        return null;
    }
}

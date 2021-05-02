package com.sika.code.standard.base.executor;

import cn.hutool.core.util.StrUtil;
import com.sika.code.basic.pojo.dto.ServiceResult;
import com.sika.code.basic.util.Assert;
import com.sika.code.common.factory.BeanFactory;
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
     * 传入上下文对象 返回执行后的结果
     * </p>
     *
     * @param context
     * @return Response
     * @author daiqi
     * @date 2021/1/4 15:45
     */
    public ServiceResult execute(BaseStandardContext context) {
        Class<? extends BaseStandardExecutor> executorClass = context.getExecutorClass();
        Assert.verifyObjNullMsg(executorClass, "执行者类为空");
        BaseStandardExecutor executor = getExecutor(executorClass);
        Assert.verifyObjNullMsg(executor, StrUtil.format("执行者Class【{}】对应的执行者对象不存在", executorClass));
        return executor.execute(context);
    }

    protected BaseStandardExecutor getExecutor(Class<? extends BaseStandardExecutor> executorClass) {
        if (executorClass == null) {
            return null;
        }
        return BeanFactory.newInstance(executorClass);
    }

}

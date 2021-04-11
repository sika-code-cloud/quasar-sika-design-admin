package com.sika.code.standard.base.pojo.context;

import com.sika.code.basic.pojo.context.BaseContext;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.standard.base.pojo.executor.BaseStandardExecutor;

/**
 * @author sikadai
 * @Description: 基础标准上下文
 * @date 2021/4/421:29
 */
public abstract class BaseStandardContext implements BaseContext {
    private Class<? extends BaseStandardExecutor> executorClass;
    /**
     * 初始化执行class
     */
    protected abstract Class<? extends BaseStandardExecutor> buildExecutorClass();


    @Override
    public final void init() {
        this.initCustomer();
    }

    public void initCustomer() {

    }

    @Override
    public void clear() {

    }

    public Class<? extends BaseStandardExecutor> getExecutorClass() {
        if (BaseUtil.isNull(this.executorClass)) {
            this.executorClass = buildExecutorClass();
        }
        return this.executorClass;
    }
}

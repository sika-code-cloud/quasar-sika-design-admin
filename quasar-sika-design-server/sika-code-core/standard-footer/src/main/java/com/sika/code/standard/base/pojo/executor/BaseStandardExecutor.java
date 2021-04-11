package com.sika.code.standard.base.pojo.executor;

import com.sika.code.basic.pojo.dto.ServiceResult;
import com.sika.code.basic.pojo.executor.BaseExecutor;
import com.sika.code.standard.base.pojo.context.BaseStandardContext;

/**
 * @author sikadai
 * @Description: 基础标准执行者
 * @date 2021/4/421:29
 */
public abstract class BaseStandardExecutor<Context extends BaseStandardContext> implements BaseExecutor<Context> {
    /**
     * 是否执行 --- 防止重复执行
     */
    protected boolean execute;
    /**
     * 贯穿整个执行者生命周期得上下文对象
     */
    protected Context context;
    /**
     * 执行得结果
     */
    protected ServiceResult result;

    public final ServiceResult execute(Context context) {
        try {
            this.context = context;
            if (!needExecute()) {
                return result;
            }
            this.context.init();
            executeBefore();
            result = doExecute();
            executeAfter();
            execute = true;
        } catch (Exception e) {
            executeException(e);
        } finally {
            this.context.clear();
            executeFinally();
        }
        return result;
    }

    /**
     * 是否需要执行 --- 子类可以通过复写该方法处理执行逻辑
     */
    protected boolean needExecute() {
        // 如果已经执行成功，则默认不需要再次执行
        if (execute) {
            return false;
        }
        return true;
    }

    /**
     * 执行前的方法
     * 1. 初始化
     * 2. 前置校验
     * 3. 异常阻断 可以通过抛出异常中断执行执行过程
     */
    protected void executeBefore() {

    }

    /**
     * <p>
     * 具体执行的抽象方法
     * </p>
     *
     * @return ResponseBO
     * @author daiqi
     * @date 2019/6/16 23:19
     */
    protected abstract ServiceResult doExecute();

    /**
     * 执行之后调用的方法 -- 一般用作执行完成后的扫尾处理
     */
    protected void executeAfter() {

    }

    /**
     * 执行的异常处理 -- 如果执行过程中抛出异常获取其他的，可以通过在此处进行定制化处理
     */
    protected void executeException(Exception e) {
        if (e instanceof RuntimeException) {
            throw (RuntimeException) e;
        }
        throw new RuntimeException(e);
    }

    /**
     * 执行Finally块，此处可以通过清理相关资源
     */
    protected void executeFinally() {

    }
}

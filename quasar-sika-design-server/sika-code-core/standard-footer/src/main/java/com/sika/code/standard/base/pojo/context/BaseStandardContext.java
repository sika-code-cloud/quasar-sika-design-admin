package com.sika.code.standard.base.pojo.context;

import com.sika.code.basic.pojo.context.BaseContext;

/**
 * @author sikadai
 * @Description: 基础标准上下文
 * @date 2021/4/421:29
 */
public abstract class BaseStandardContext implements BaseContext {

    /**
     * 获取执行者的key
     */
    public abstract String getExecutorKey();

    @Override
    public void clear() {

    }
}

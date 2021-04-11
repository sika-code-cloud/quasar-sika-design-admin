package com.sika.code.basic.pojo.context;

/**
 * @author sikadai
 * @Description: 基础上下文
 * @date 2021/4/421:26
 */
public interface BaseContext {
    /** 初始化 */
    void init();
    /** 清理 */
    void clear();
}

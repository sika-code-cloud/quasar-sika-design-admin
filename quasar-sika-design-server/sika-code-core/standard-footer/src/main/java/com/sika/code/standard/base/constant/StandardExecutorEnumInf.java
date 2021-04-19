package com.sika.code.standard.base.constant;

import com.sika.code.basic.constant.CodeEnumInf;
import com.sika.code.standard.base.pojo.executor.BaseStandardExecutor;

/**
 * @author sikadai
 * @Description: 基础执行者枚举接口
 * @date 2021/4/422:51
 */
public interface StandardExecutorEnumInf extends CodeEnumInf {

    /**
     * 执行者类型
     */
    Integer getType();
    Class<? extends BaseStandardExecutor> getExecutorClass();
}

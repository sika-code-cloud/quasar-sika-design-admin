package com.quasar.sika.design.server.common.controller;

import com.quasar.sika.design.server.common.executor.manager.ExecutorManager;
import com.sika.code.basic.pojo.dto.ServiceResult;
import com.sika.code.standard.base.controller.BaseStandardController;
import com.sika.code.standard.base.pojo.context.BaseStandardContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sikadai
 * @Description: 基础sika设置服务controller
 * @date 2021/4/519:30
 */
public class BaseSikaDesignServerController extends BaseStandardController {
    @Autowired
    protected ExecutorManager executorManager;

    protected ServiceResult execute(BaseStandardContext context) {
        return executorManager.execute(context);
    }
}

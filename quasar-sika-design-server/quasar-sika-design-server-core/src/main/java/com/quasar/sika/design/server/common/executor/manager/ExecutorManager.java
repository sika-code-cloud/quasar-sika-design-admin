package com.quasar.sika.design.server.common.executor.manager;

import com.sika.code.basic.pojo.dto.ServiceResult;
import com.sika.code.standard.base.executor.StandardExecutorManager;
import com.sika.code.standard.base.pojo.context.BaseStandardContext;
import org.springframework.stereotype.Component;

/**
 * @author sikadai
 * @Description: 执行者管理者
 * @date 2021/4/423:25
 */
@Component
public class ExecutorManager extends StandardExecutorManager {
    @Override
    public ServiceResult execute(BaseStandardContext context) {
        return super.execute(context);
    }
}

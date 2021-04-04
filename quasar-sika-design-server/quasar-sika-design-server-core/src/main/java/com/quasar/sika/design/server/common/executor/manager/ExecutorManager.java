package com.quasar.sika.design.server.common.executor.manager;

import cn.hutool.core.util.StrUtil;
import com.quasar.sika.design.server.common.executor.enums.ExecutorEnum;
import com.sika.code.basic.pojo.dto.ServiceResult;
import com.sika.code.basic.util.Assert;
import com.sika.code.common.factory.BeanFactory;
import com.sika.code.common.util.EnumUtil;
import com.sika.code.standard.base.constant.StandardExecutorEnumInf;
import com.sika.code.standard.base.executor.StandardExecutorManager;
import com.sika.code.standard.base.pojo.context.BaseStandardContext;
import com.sika.code.standard.base.pojo.executor.BaseStandardExecutor;
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

    @Override
    protected BaseStandardExecutor getExecutor(String executorKey) {
        StandardExecutorEnumInf enumInf = EnumUtil.find(ExecutorEnum.class, "getCode", executorKey);
        Assert.verifyObjNullMsg(enumInf, StrUtil.format("执行者KEY【{}】对应的执行者枚举不存在，请核实", executorKey));
        return BeanFactory.newInstance(enumInf.getExecutorClass());
    }
}

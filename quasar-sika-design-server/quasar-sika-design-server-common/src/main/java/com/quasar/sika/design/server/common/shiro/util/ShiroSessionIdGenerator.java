package com.quasar.sika.design.server.common.shiro.util;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.quasar.sika.design.server.common.shiro.constant.ShiroConstant;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;

/**
 *  <p> 自定义SessionId生成器 </p>
 *
 * @description :
 * @author : zhengqing
 * @date : 2019/8/23 15:47
 */
public class ShiroSessionIdGenerator implements SessionIdGenerator {
    /**
     * 实现SessionId生成
     */
    @Override
    public Serializable generateId(Session session) {
        return StrUtil.format(ShiroConstant.REDIS_PREFIX_LOGIN, IdUtil.randomUUID());
    }
}

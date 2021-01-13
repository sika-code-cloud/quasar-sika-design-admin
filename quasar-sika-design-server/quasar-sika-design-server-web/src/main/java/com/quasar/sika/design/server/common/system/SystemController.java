package com.quasar.sika.design.server.common.system;

import com.quasar.sika.design.server.common.system.pojo.request.SystemRequest;
import com.quasar.sika.design.server.common.system.pojo.response.SystemResponseBO;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统控制器
 * @author daiqi
 * @create 2021-01-06 23:02
 */
@RestController(value = "systemController")
@RequestMapping("system")
public class SystemController extends BaseStandardController {
    /**
     * 授权用户绑定-校验验证码
     */
    @RequestMapping("/get_system_info/anon")
    @ResponseBody
    public Result getSystemInfo(@RequestBody SystemRequest request) {
        return success(new SystemResponseBO().build());
    }
}

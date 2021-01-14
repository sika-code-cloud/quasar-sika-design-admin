package com.quasar.sika.design.server.common.shiro.util;

import com.sika.code.basic.errorcode.BaseErrorCode;
import com.sika.code.common.json.util.JSONUtil;
import com.sika.code.result.Result;
import com.sika.code.result.generator.ResultGenerator;
import org.apache.shiro.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletUtils {
    private static String X_REQUESTED_WITH = "X-Requested-With";
    private static String XML_HTTP_REQUEST = "XMLHttpRequest";

    public static void writeForMsg(ResultGenerator resultGenerator, HttpServletResponse httpResponse, BaseErrorCode errorCode, String msg) {
        Result result = resultGenerator.generateResultError(errorCode, msg);
        httpResponse.setCharacterEncoding("UTF-8");
        cn.hutool.extra.servlet.ServletUtil.write(httpResponse, JSONUtil.toJSONString(result), "text/json,charset=UTF-8");
    }

    public static boolean isAjaxReq(HttpServletRequest request){
        String requestWith = request.getHeader(X_REQUESTED_WITH);
        return XML_HTTP_REQUEST.equals(requestWith);
    }
}

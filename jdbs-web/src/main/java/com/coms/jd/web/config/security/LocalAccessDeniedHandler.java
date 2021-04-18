package com.coms.jd.web.config.security;

import com.coms.jd.beans.code.ResultCode;
import com.coms.jd.utils.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocalAccessDeniedHandler implements AccessDeniedHandler {
    /**
     * 已登陆用户访问无权限资源
     * */
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e)
            throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        Result result = new Result();
        result.setReturnCode(ResultCode.LOGIN_NO_POWER);
        result.setReturnCode("已登陆用户访问无权限资源");
        httpServletResponse.getWriter().print(result);
    }
}

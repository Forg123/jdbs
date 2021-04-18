package com.coms.jd.web.config.security;

import com.coms.jd.beans.code.ResultCode;
import com.coms.jd.utils.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class LocalAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * 未登陆用户访问无权限资源
     * */
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e)
            throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        Result result = new Result();
        result.setReturnCode(ResultCode.NO_LOGIN_NO_POWER);
        result.setReturnMessage("未登陆用户访问无权限资源");
        httpServletResponse.getWriter().print("未登陆用户访问无权限资源");
    }
}

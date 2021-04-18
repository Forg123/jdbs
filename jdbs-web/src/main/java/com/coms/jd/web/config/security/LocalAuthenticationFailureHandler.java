package com.coms.jd.web.config.security;

import com.coms.jd.beans.code.ResultCode;
import com.coms.jd.utils.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocalAuthenticationFailureHandler implements AuthenticationFailureHandler {
    /**
     * 登陆失败执行
     * */
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e)
            throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        Result result = new Result();
        result.setReturnCode(ResultCode.LOGIN_FILE);
        result.setReturnMessage("登陆失败");
        httpServletResponse.getWriter().print(result);
    }
}

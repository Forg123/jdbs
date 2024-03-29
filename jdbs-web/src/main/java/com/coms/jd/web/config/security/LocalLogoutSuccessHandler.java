package com.coms.jd.web.config.security;

import com.coms.jd.beans.code.ResultCode;
import com.coms.jd.utils.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocalLogoutSuccessHandler implements LogoutSuccessHandler {
    /**
     * 注销成功执行返回
     * */
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication)
            throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        Result result = new Result();
        result.setReturnCode(ResultCode.LOGINOUT_SUCCESS);
        result.setReturnMessage("注销成功");
        httpServletResponse.getWriter().print(result);
    }
}

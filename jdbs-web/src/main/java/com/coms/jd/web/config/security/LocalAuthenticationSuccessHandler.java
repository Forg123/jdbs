package com.coms.jd.web.config.security;

import com.coms.jd.utils.Result;
import com.coms.jd.utils.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 项目未用到
 * */
@Component
public class LocalAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    /**  带有一个过滤器，功能更加完善
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication)
            throws IOException, ServletException {
    }
     */
    /**
     * 登陆成功执行
     * */
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().print("登陆成功");
    }
}

package com.coms.jd.web.config.security;

import com.coms.jd.utils.UserInfo;
import com.coms.jd.utils.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class jwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private UserInfo userInfo;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (token != null){
            //判断token是否已经过期,if里面为未过期
            boolean isToknExpried = false;
            try {
                isToknExpried = !jwtTokenUtil.isTokenExpired(token);
            }catch (Exception e){
                //throw new UsernameNotFoundException("解析出错");
                System.out.println("解析出错");
            }
            if (isToknExpried){
                //从token中获取负载
                Map<String , Object> params = jwtTokenUtil.getClaimsFromToken(token);
                userInfo.setUserAccount((String) params.get("userAccount"));
                userInfo.setRoleLevel((int)params.get("roleLevel"));
                userInfo.setRoles((List<String>) params.get("userRoles"));
                UserDetails userDetails = this.userDetailService.loadUserByUsername((String) params.get("userAccount"));
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //设置用户登录状态
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}

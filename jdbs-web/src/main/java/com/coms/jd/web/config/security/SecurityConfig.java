package com.coms.jd.web.config.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.coms.jd.beans.code.ResultCode;
import com.coms.jd.beans.entity.sys.MenusRelstion;
import com.coms.jd.service.sys.GetMenusByRole;
import com.coms.jd.utils.Result;
import com.coms.jd.utils.jwt.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * SpringSecurity配置类
 * */
@Configuration
@EnableWebSecurity
@Component
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private Md5PasswordEncoder md5PasswordEncoder;
    @Autowired
    private CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;
    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;
    @Reference
    private GetMenusByRole getMenusByRole;
    /**
     * 授权管理
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        md5PasswordEncoder.setUserName("");
        auth.userDetailsService(userDetailService).passwordEncoder(md5PasswordEncoder);
    }
    /**
     * 这里面配置的接口你不需要权限就能访问
     * */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/register/sendVerificationCode",
                "/register/checkOutCode",
                "/findpassword/sendmail",
                "/findpassword/checkcode");
    }
    /**
     * 权限控制
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 设置拦截规则
        http.authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                o.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
                o.setAccessDecisionManager(myAccessDecisionManager);
                return o;
            }
        });
        //处理跨域
        http.cors().configurationSource(CorsConfigurationSource());
        // 自定义登录信息
        http.csrf().disable().formLogin()
                .passwordParameter("password")
                .usernameParameter("username")
                .permitAll();
        http.addFilterAfter(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        // 自定义异常处理器
        http.exceptionHandling() // 异常处理器
                // 用来解决匿名用户访问无权限资源时的异常
                .authenticationEntryPoint(new LocalAuthenticationEntryPoint())
                // 用来解决认证过的用户访问无权限资源时的异常
                .accessDeniedHandler(new LocalAccessDeniedHandler());
        // 自定义注销信息
        http.logout() //
                .logoutUrl("dologout") // 登出验证地址, 即RequestMapping地址
                .logoutSuccessHandler(new LocalLogoutSuccessHandler()) // 登录验证成功后, 执行的内容
                // .logoutSuccessUrl("/login?logout") // 登录验证成功后, 跳转的页面, 如果自定义返回内容, 请使用logoutSuccessHandler方法
                .deleteCookies("JSESSIONID") // 退出登录后需要删除的cookies名称
                .invalidateHttpSession(true) // 退出登录后, 会话失效
                .permitAll();
        // 禁用缓存
        http.headers().cacheControl();
        // 由于使用的是JWT，我们这里不需要csrf,这里是能使用jwt的关键
        http.csrf()
                .disable()
                .sessionManagement()// 基于token，所以不需要session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 添加JWT filter
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    public jwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new jwtAuthenticationTokenFilter();
    }
    /**
     * 登陆加密
     * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }
    /** */
    @Bean
    public LoginFilter  customAuthenticationFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        // 前端的登录请求地址
        loginFilter.setFilterProcessesUrl("/dologin");
        // 登录成功后返回给前端的json数据
        loginFilter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                resp.setCharacterEncoding("utf-8");
                resp.setContentType("application/json;charset=utf-8");
                //获取用户对象集
                Map<String , Object> userInfo = new HashMap<>();
                LocalUserDetails principal = (LocalUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                /**
                 * 1、根据权限获取到该用户所对应的菜单
                 * 2、根据获取到的数据给菜单排序
                 * */
                List<MenusRelstion> pars = principal.getRoles();
                getMenus(pars);
                userInfo.put("userAccount" , principal.getUsername());
                Map<String , Object> params = new HashMap<>();
                params.put("token" , jwtTokenUtil.createTokenByUser(userInfo));
                Result result = new Result();
                result.setReturnCode(ResultCode.LOGIN_SUCCESS);
                result.setReturnMessage("登陆成功");
                result.setBean(params);
                PrintWriter out = resp.getWriter();
                out.write(new ObjectMapper().writeValueAsString(result));
                out.flush();
                out.close();
            }
        });
        // 登录失败后返回给前端的json数据
        loginFilter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                resp.setCharacterEncoding("utf-8");
                resp.setContentType("application/json;charset=utf-8");
                resp.setHeader("Access-Control-Allow-Origin" , req.getHeader("Origin"));
                Result result = new Result();
                result.setReturnCode("4001");
                result.setReturnMessage("登陆失败");
//              resp.getWriter().print(result.retData()); //这种方式有问题
                PrintWriter out = resp.getWriter();
                out.write(new ObjectMapper().writeValueAsString(result));
                out.flush();
                out.close();
            }
        });
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        return loginFilter;
    }
    /**
     * 处理跨域
     * */
    private CorsConfigurationSource CorsConfigurationSource() {
        CorsConfigurationSource source =   new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");    //同源配置，*表示任何请求都视为同源，若需指定ip和端口可以改为如“localhost：8080”，多个以“，”分隔；
        corsConfiguration.addAllowedHeader("*");//header，允许哪些header，本案中使用的是token，此处可将*替换为token；
        corsConfiguration.addAllowedMethod("*");    //允许的请求方法，PSOT、GET等
        ((UrlBasedCorsConfigurationSource) source).registerCorsConfiguration("/**",corsConfiguration); //配置允许跨域访问的url
        return source;
    }
    /**
     * 根据权限获取到菜单列表
     * 解决思路：父ID相同的为一组子节点
     * */
    private List<Object> getMenus(List<MenusRelstion> params){
        //List<MenusRelstion> a = getMenusByRole.getMenusByRoles("p00001");
        List<Object>  menus = new ArrayList<>();
        for (MenusRelstion menu : params){
            //父节点,找到这个父节点下面的所有子节点
            if (menu.getMenuPid() == 0){
                List<Object> me1 = new ArrayList<>();
                //
                menus.add(me1);
            }
        }
        return menus;
    }
}

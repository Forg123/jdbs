package com.coms.jd.web.config.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.coms.jd.service.sys.SelectRoleByUrl;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * 用于动态授权
 * */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Reference
    private SelectRoleByUrl selectRoleByUrl;
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //获取当前请求的url
        String url = ((FilterInvocation) object).getFullRequestUrl();
        //获取当前url需要的权限
        List<String> roles = selectRoleByUrl.selectRoleByUrl(url);
        //判断有没有查出来数据，若没有则返回一个默认的角色
        int rolesSize = roles.size();
        if (rolesSize <= 0){
            //如果没有则返回一个默认的角色
            return SecurityConfig.createList("UNKNOW");
        }
        String [] retRoles = new String[rolesSize];
        for (int i = 0; i < rolesSize; i++) {
            retRoles[i] = roles.get(i);
        }
        return SecurityConfig.createList(retRoles);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}

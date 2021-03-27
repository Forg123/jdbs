package com.coms.jd.web.config.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.coms.jd.service.sys.SelectUserByUserAccount;
import com.coms.jd.service.sys.SelectUserRoleByUserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 动态授权
 * */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Reference
    private SelectUserRoleByUserAccount selectUserRoleByUserAccount;
    @Autowired
    private LocalUserDetails localUserDetails;
    @Reference
    private SelectUserByUserAccount selectUserByUserAccount;
    private String userAccount;
    public String getUserAccount() {
        return userAccount;
    }
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Map<String , Object> userInfo = selectUserByUserAccount.selectUserByUserAccount(userName);
        //从数据库获取当前用户角色列表
        List<String> roleCode  = selectUserRoleByUserAccount.selectUserRoleByUserAccount(userName);
        //授权
        Map<RequestMatcher , Collection<ConfigAttribute>> map = new HashMap<>();
        if (userInfo != null){
            String userAccount = (String) userInfo.get("userAccount");
            String password = (String) userInfo.get("userPwd");
            localUserDetails.setUserName(userAccount);
            localUserDetails.setPassword(password);
            localUserDetails.setRoles(roleCode);
            localUserDetails.setEnabled(true);
            localUserDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(
                    String.join("," , roleCode)
            ));
            return localUserDetails;
        }else {
            System.out.println("当前用户不存在");
            throw new UsernameNotFoundException("当前用户不存在");
        }
    }
}

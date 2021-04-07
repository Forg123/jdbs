package com.coms.jd.utils;


import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 登陆人用户信息存放
 * */
@Component
public class UserInfo {
    private String userAccount;
    private String userRole;
    private List<String> roles;
    private int roleLevel;
    public UserInfo() {
    }
    public UserInfo(String userAccount , String userRole) {
        this.userAccount = userAccount;
        this.userRole = userRole;
    }
    public String getUserAccount() {
        return userAccount;
    }
    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    public int getRoleLevel() {
        return roleLevel;
    }
    public void setRoleLevel(int roleLevel) {
        this.roleLevel = roleLevel;
    }
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
    public String getUserRole() {
        return userRole;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}

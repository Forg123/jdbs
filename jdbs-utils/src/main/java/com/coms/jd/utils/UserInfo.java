package com.coms.jd.utils;


import org.springframework.stereotype.Component;

/**
 * 登陆人用户信息存放
 * */
@Component
public class UserInfo {
    private String userAccount;
    public UserInfo() {
    }
    public UserInfo(String userAccount) {
        this.userAccount = userAccount;
    }
    public String getUserAccount() {
        return userAccount;
    }
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
}

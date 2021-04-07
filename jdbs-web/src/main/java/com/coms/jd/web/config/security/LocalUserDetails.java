package com.coms.jd.web.config.security;

import com.coms.jd.beans.entity.sys.MenusRelstion;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
public class LocalUserDetails implements UserDetails {
    /**
     * 用户全安先集合
     * */
    private Collection<? extends GrantedAuthority> authorities;
    /**
     * 用户菜单
     */
    private List<MenusRelstion> roleMenu;
    /**
     * 用户权限
     * */
    private List<String> roles;
    /**
     * 权限级别
     * */
    private int roleLevel;
    /**
     * 用户密码
     * */
    private String password;
    /**
     * 用户名
     * */
    private String userName;
    /**
     * 用户名知否已经过期
     * */
    private boolean accountNonExpired;
    /**
     * 用户名是否已经被锁定
     * */
    private boolean accountNonLocked;
    /**
     * 密码是否已经过期
     * */
    private boolean credentialsNonExpired;
    /**
     * 账户是否启用
     * */
    private boolean enabled;
    public int getRoleLevel() {
        return roleLevel;
    }
    public void setRoleLevel(int roleLevel) {
        this.roleLevel = roleLevel;
    }
    public List<MenusRelstion> getRoleMenu() {
        return roleMenu;
    }
    public void setRoleMenu(List<MenusRelstion> roleMenu) {
        this.roleMenu = roleMenu;
    }
    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }
    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 给用户分配权限
     * */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }
    /**
     * 账户是否过期
     * */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /**
     * 指定账户是否未锁定
     * */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /**
     * 指定密码是否未过期
     * */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**
     * 指定用户是否已启用
     * */
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

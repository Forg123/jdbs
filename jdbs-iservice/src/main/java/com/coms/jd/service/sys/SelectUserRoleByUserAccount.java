package com.coms.jd.service.sys;

import java.util.List;

public interface SelectUserRoleByUserAccount {
    /**
     * 根据用户名查看用户权限
     * */
    List<String> selectUserRoleByUserAccount(String userAccount);
}

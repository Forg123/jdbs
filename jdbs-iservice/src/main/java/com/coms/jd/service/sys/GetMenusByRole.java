package com.coms.jd.service.sys;

import com.coms.jd.beans.entity.sys.MenusRelstion;

import java.util.List;

/**
 * 菜单权限
 * */
public interface GetMenusByRole {
    /**
     * 根据用户权限获取菜单列表
     * */
    List<MenusRelstion> getMenusByRoles(String role);
}

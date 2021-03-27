package com.coms.jd.service.sys;

import com.coms.jd.beams.entity.sys.MenusRelstion;

import java.util.List;
import java.util.Map;

/**
 * 菜单权限
 * */
public interface GetMenusByRole {
    /**
     * 根据用户权限获取菜单列表
     * */
    List<MenusRelstion> getMenusByRole(String role);
}

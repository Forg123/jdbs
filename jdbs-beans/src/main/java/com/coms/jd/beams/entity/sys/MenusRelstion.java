package com.coms.jd.beams.entity.sys;

import lombok.Data;

/**
 * 菜单全西安对应表
 * */
@Data
public class MenusRelstion {
    /**
     * 菜单ID
     * */
    private int menuId;
    /**
     * 菜单父ID
     * */
    private int menuPid;
    /**
     * 菜单类型
     * */
    private String menuType;
}

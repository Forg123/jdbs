package com.coms.jd.beans.entity.sys;

import lombok.Data;

import java.io.Serializable;

/**
 * 菜单全西安对应表
 * */
@Data
public class MenusRelstion implements Serializable {
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

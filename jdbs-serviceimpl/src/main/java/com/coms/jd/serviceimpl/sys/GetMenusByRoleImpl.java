package com.coms.jd.serviceimpl.sys;

import com.alibaba.dubbo.config.annotation.Service;
import com.coms.jd.beams.entity.sys.MenusRelstion;
import com.coms.jd.dao.sys.MenuRoleRelationDao;
import com.coms.jd.service.sys.GetMenusByRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 菜单列表实现类
 * */
@Component
@Service
public class GetMenusByRoleImpl implements GetMenusByRole {
    @Autowired
    private MenuRoleRelationDao menuRoleRelationDao;
    @Override
    public List<MenusRelstion> getMenusByRole(String role) {
        return menuRoleRelationDao.getMenusByRole(role);
    }
}

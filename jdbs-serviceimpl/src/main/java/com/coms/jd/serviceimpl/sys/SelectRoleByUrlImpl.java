package com.coms.jd.serviceimpl.sys;

import com.alibaba.dubbo.config.annotation.Service;
import com.coms.jd.dao.sys.MenuRoleRelationDao;
import com.coms.jd.service.sys.SelectRoleByUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Service
public class SelectRoleByUrlImpl implements SelectRoleByUrl {
    @Autowired
    private MenuRoleRelationDao menuRoleRelationDao;
    @Override
    public List<String> selectRoleByUrl(String url) {
        return menuRoleRelationDao.selectRoleByUrl(url);
    }
}

package com.coms.jd.serviceimpl.sys;

import com.alibaba.dubbo.config.annotation.Service;
import com.coms.jd.dao.sys.SysUserRoleDao;
import com.coms.jd.service.sys.SelectUserRoleByUserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Service
@Component
public class SelectRoleByUserAccountImpl implements SelectUserRoleByUserAccount {
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    @Override
    public List<String> selectUserRoleByUserAccount(String userAccount) {
        return sysUserRoleDao.selectUserRole(userAccount);
    }
}

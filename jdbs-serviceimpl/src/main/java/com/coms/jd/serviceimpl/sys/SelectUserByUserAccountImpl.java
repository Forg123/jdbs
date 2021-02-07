package com.coms.jd.serviceimpl.sys;

import com.alibaba.dubbo.config.annotation.Service;
import com.coms.jd.dao.sys.SysUserDao;
import com.coms.jd.service.sys.SelectUserByUserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
@Service
public class SelectUserByUserAccountImpl implements SelectUserByUserAccount {
    @Autowired
    private SysUserDao sysUserDao;
    @Override
    public Map<String, Object> selectUserByUserAccount(String userAccount) {
        return sysUserDao.selectUserByAccount(userAccount);
    }
}

package com.coms.jd.serviceimpl.sys;

import com.alibaba.dubbo.config.annotation.Service;
import com.coms.jd.dao.sys.SysUserDao;
import com.coms.jd.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户信息
 * */
@Component
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;
    @Override
    public int updateLoginTime(String userAccount) {
        return sysUserDao.updateLoginToome(userAccount);
    }
}

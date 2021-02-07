package com.coms.jd.serviceimpl.sys;

import com.alibaba.dubbo.config.annotation.Service;
import com.coms.jd.dao.sys.SysUserDao;
import com.coms.jd.service.sys.GetUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Service
public class GetUserImpl implements GetUser {
    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public Map<String, Object> getUserIInfo() {
         Map<String , Object> params = new HashMap<>();
         return sysUserDao.selectUser(params);
    }
}

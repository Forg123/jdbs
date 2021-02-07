package com.coms.jd;

import com.coms.jd.dao.sys.MenuRoleRelationDao;
import com.coms.jd.dao.sys.SysUserDao;
import com.coms.jd.service.sys.GetUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestDemo {
    @Autowired
    private GetUser getUser;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private MenuRoleRelationDao menuRoleRelationDao;
    @Test
    void testDemo(){
//        System.out.println(sysUserDao.selectUserByAccount("qiqi"));
//        System.out.println(menuRoleRelationDao.selectRoleByUrl("http://localhost:8001/admin/sys").toString());
    }
}

package com.coms.jd;

import com.alibaba.dubbo.config.annotation.Reference;
import com.coms.jd.beans.entity.sys.MenusRelstion;
import com.coms.jd.service.sys.GetMenusByRole;
import com.coms.jd.utils.jwt.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class TestDemo {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Reference
    private  GetMenusByRole getMenusByRole;
    @Test
    void testDemo(){
//        List<MenusRelstion> params = getMenusByRole.getMenusByRoles("p0001");
//        System.out.println();
    }
}


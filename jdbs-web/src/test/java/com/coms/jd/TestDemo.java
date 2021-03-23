package com.coms.jd;

import com.coms.jd.utils.jwt.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TestDemo {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Test
    void testDemo(){
        /**
        Map<String , Object> params = new HashMap<>();
        params.put("useraccount" , "zhangsan");
        params.put("username" , "张三");
        String token = jwtTokenUtil.generateToken(params);
        System.out.println(token);
        System.out.println(jwtTokenUtil.getClaimsFromToken(token));
         */
        String a = "1";
        String b = "";
        if (a.equals("") && b.equals("")){
            System.out.println("==");
        }else {
            System.out.println("+++");
        }
    }
}

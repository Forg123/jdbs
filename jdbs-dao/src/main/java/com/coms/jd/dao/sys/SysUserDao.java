package com.coms.jd.dao.sys;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper
public interface SysUserDao {
    Map<String , Object> selectUser(Map<String, Object> params);
    /**
     * 根据用户名查询用户的基本信息
     * */
    Map<String , Object> selectUserByAccount(@Param("userAccount") String userAccount);
    /**
     * 根据用户名修改登陆时间
     * */
    int updateLoginToome(@Param("userAccount") String userAccount);
}

package com.coms.jd.dao.sys;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SysUserRoleDao {
    /**
     * 根据用户id查询用户角色
     * */
    List<String> selectUserRole(@Param("userAccount") String userAccount);
}

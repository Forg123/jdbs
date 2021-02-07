package com.coms.jd.dao.sys;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MenuRoleRelationDao {
    /**
     * 根据url获取对应的权限
     * */
    List<String> selectRoleByUrl(@Param("menuUrl") String url);
}

package com.coms.jd.dao.sys;

import com.coms.jd.beams.entity.sys.MenusRelstion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface MenuRoleRelationDao {
    /**
     * 根据url获取对应的权限
     * */
    List<String> selectRoleByUrl(@Param("menuUrl") String url);
    /**
     * 根据权限获取到所对应的菜单(返回对应的菜单ID,父级菜单ID，菜单类型)
     * */
    List<MenusRelstion> getMenusByRole(@Param("role") String role);
}

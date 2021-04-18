package com.coms.jd.web.controller.sys;

import com.coms.jd.utils.Input;
import com.coms.jd.utils.Result;
import com.coms.jd.utils.Rout;
import com.coms.jd.utils.UserInfo;
import com.coms.jd.web.csf.CsfUtilsCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

/**
 * 基础的公共接口
 * */
@RestController
@RequestMapping("/publicBase")
public class PublicBaseController {
    @Autowired
    private CsfUtilsCall csf;
    @Autowired
    private UserInfo userInfo;
    /**
     * 根据角色编码查询对应的菜单
     * */
    @CrossOrigin
    @RequestMapping("/getRoleMenus")
    @Rout(controllerName = "publicBaseController" , moduleName = Rout.ModuleType.JDMAN , methodName = "getRoleMenus")
    public Result getRoleMenus(@RequestBody Input input) throws ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Result result = new Result();
        String roleId = (String) input.getParams().get("roleId");
        if (roleId == null || roleId == ""){
            input.getParams().put("roleId" , userInfo.getRoles().get(0));
        }
        return csf.csfToResult(input);
    }
    /**
     * 获取超级管理员的用户
     * */
    @CrossOrigin
    @RequestMapping("/getAdminUsers")
    @Rout(controllerName = "publicBaseController" , moduleName = Rout.ModuleType.JDMAN , methodName = "getAdminUsers")
    public Result getAdminUsers(@RequestBody Input input) throws ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return csf.csfToResult(input);
    }

}

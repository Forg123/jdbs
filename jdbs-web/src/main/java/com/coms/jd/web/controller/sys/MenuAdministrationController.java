package com.coms.jd.web.controller.sys;

import com.coms.jd.beans.code.ResultCode;
import com.coms.jd.utils.Input;
import com.coms.jd.utils.Result;
import com.coms.jd.utils.Rout;
import com.coms.jd.utils.UserInfo;
import com.coms.jd.web.csf.CsfUtilsCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 * */
@RestController
@RequestMapping("/menuAdministration")
public class MenuAdministrationController {
    @Autowired
    private CsfUtilsCall csf;
    @Autowired
    private UserInfo userInfo;
    /**
     * 修改权限
     * */
    @RequestMapping("/updateRoleMenus")
    @Rout(controllerName = "menuAdministrationController" , moduleName = Rout.ModuleType.JDMAN , methodName = "updateRoleMenus")
    public Result updateRoleMenus(@RequestBody Input input) throws ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Result result = new Result();
        Map<String , Object> params = input.getParams();
        String roleId = (String) params.get("roleId");
        List<String> addMenus = (List<String>) params.get("addMenus");
        List<String> outMenus = (List<String>) params.get("outMenus");
        if (roleId == null || roleId == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("修改权限，角色名不能为空");
            return result;
        }
        if (addMenus == null && outMenus == null){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("修改的菜单不能未空");
            return result;
        }
        return csf.csfToResult(input);
    }
}

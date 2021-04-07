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
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * 用户操作自己信息
 * */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {
    @Autowired
    private CsfUtilsCall csf;
    @Autowired
    private UserInfo userInfo;
    /**
     * 用户修改自己的个人信息
     */
    @RequestMapping("/updateUserDetails")
    @Rout(controllerName = "sysUserController", moduleName = Rout.ModuleType.JDMAN, methodName = "updateUserDetails")
    public Result updateUserDetails(@RequestBody Input input) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        Result result = new Result();
        String userAccount = (String) input.getParams().get("userAccount");
        if (userAccount == null || "".equals(userAccount)) {
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("修改用户信息，用户账号不能为空");
            return result;
        }
        input.getParams().put("oldUserAccount" , userInfo.getUserAccount());
        return csf.csfToResult(input);
    }
    /**
     * 查询自己的个人信息
     * */
    @RequestMapping("/getUserInfo")
    @Rout(controllerName = "sysUserController", moduleName = Rout.ModuleType.JDMAN, methodName = "getUserInfo")
    public Result getUserInfo(@RequestBody Input input) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        input.getParams().put("userAccount" , userInfo.getUserAccount());
        return csf.csfToResult(input);
    }
    /**
     * 查询该用户名是否正在使用
     * */
    @RequestMapping("/checkUserAccountIsUse")
    @Rout(controllerName = "sysUserController", moduleName = Rout.ModuleType.JDMAN, methodName = "checkUserAccountIsUse")
    public Result checkUserAccountIsUse(@RequestBody Input input) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        Result result = new Result();
        String userAccount = (String) input.getParams().get("userAccount");
        if (userAccount == null || "".equals(userAccount)){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("检查用户名是否可用，用户民不能为空");
            return result;
        }
        return csf.csfToResult(input);
    }
    /**
     * 根据条件查询用户信息(主要包括权限)
     * */
    @RequestMapping("/getUsers")
    @Rout(controllerName = "sysUserController", moduleName = Rout.ModuleType.JDMAN, methodName = "getUsers")
    public Result getUsers(@RequestBody Input input) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        //获取当前登陆人的用户权限
        input.getParams().put("roleLevel" , userInfo.getRoleLevel());
        return csf.csfToResult(input);
    }
    /**
     * 为用户提升修改权限
     * */
    @RequestMapping("/updateUserRole")
    @Rout(controllerName = "sysUserController", moduleName = Rout.ModuleType.JDMAN, methodName = "updateUserRole")
    public Result updateUserRole(@RequestBody Input input) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        Result result = new Result();
        String updateAccount = userInfo.getUserAccount();
        input.getParams().put("updateAccount" , updateAccount);
        input.getParams().put("roleLevel" , userInfo.getRoleLevel());
        String userId = (String) input.getParams().get("userId");
        String userRole = (String) input.getParams().get("userRole");
        if (userId == null || userId == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("修改用户的权限，用户ID不能为空");
            return result;
        }
        if (userRole == null || userRole == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("修改用户的权限，为用户授予的权限不能为空");
            return result;
        }
        return csf.csfToResult(input);
    }
    /**
     * 对用户进行操作
     * */
    public Result banUser(@RequestBody Input input)throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException{
        Result result = new Result();
        String userAccount = (String) input.getParams().get("userAccount");
        return result;
    }
}

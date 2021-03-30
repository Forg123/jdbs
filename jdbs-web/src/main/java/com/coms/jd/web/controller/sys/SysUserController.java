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
     * 按照人员信息分页查找相关用户
     * */
    @RequestMapping("/getUsers")
    @Rout(controllerName = "sysUserController", moduleName = Rout.ModuleType.JDMAN, methodName = "getUsers")
    public Result getUsers(@RequestBody Input input) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        return csf.csfToResult(input);
    }
}

package com.coms.jd.web.controller.sys;

import com.coms.jd.beans.code.ResultCode;
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
 * 人员管理页面
 * */
@RestController
@RequestMapping("/personAdministration")
public class PersonAdministrationController {
    @Autowired
    private CsfUtilsCall csf;
    @Autowired
    private UserInfo userInfo;
    /**
     * 按照条件查询用户
     */
    @CrossOrigin
    @RequestMapping("/getUsers")
    @Rout(controllerName = "personAdministrationController", moduleName = Rout.ModuleType.JDMAN, methodName = "getUsers")
    public Result getUsers(@RequestBody Input input) throws ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Result result = new Result();
        String start = (String) input.getParams().get("start");
        String limit = (String) input.getParams().get("limit");
        if (start == null || start == "") {
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("开始页不能为空");
            return result;
        }
        if (limit == null || limit == "") {
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("页码不能为空");
            return result;
        }
        input.getParams().put("start" , Integer.parseInt(start));
        input.getParams().put("limit" , Integer.parseInt(limit));
        return csf.csfToResult(input);
    }
    /**
     * 查看用户详情
     */
    @CrossOrigin
    @RequestMapping("/getUserDetail")
    @Rout(controllerName = "personAdministrationController", moduleName = Rout.ModuleType.JDMAN, methodName = "getUserDetail")
    public Result getUserDetail(@RequestBody Input input) throws ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Result result = new Result();
        String userId = (String) input.getParams().get("userId");
        if (userId == null || userId == "") {
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("查看用户详情，用户ID不允许为空");
            return result;
        }
        return csf.csfToResult(input);
    }

    /**
     * 操作用户，禁用、解除禁用
     */
    @CrossOrigin
    @RequestMapping("/optUser")
    @Rout(controllerName = "personAdministrationController", moduleName = Rout.ModuleType.JDMAN, methodName = "optUser")
    public Result optUser(@RequestBody Input input) throws ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Result result = new Result();
        String userId = (String) input.getParams().get("userId");
        String isDisable = (String) input.getParams().get("isDisable");
        if (userId == null || userId == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("禁用、解除禁用用户，用户ID不能为空");
            return result;
        }
        if (isDisable == null || isDisable == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("禁用、解除禁用用户，是否解除禁用状态不能为空");
            return result;
        }
        return csf.csfToResult(input);
    }
}

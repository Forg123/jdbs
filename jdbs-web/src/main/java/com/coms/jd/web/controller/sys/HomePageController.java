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
import java.util.Map;

/**
 * 首页
 * */
@RestController
@RequestMapping("/homePage")
public class HomePageController {
    @Autowired
    private CsfUtilsCall csf;
    @Autowired
    private UserInfo userInfo;
    /**
     * 查询用户活跃度
     * */
    @RequestMapping("/userActivity")
    @Rout(controllerName = "homePageController" , moduleName = Rout.ModuleType.JDMAN , methodName = "userActivity")
    public Result userActivity(@RequestBody Input input) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return csf.csfToResult(input);
    }
    /***
     * 查询用户新增
     * */
    @RequestMapping("/userNewAdd")
    @Rout(controllerName = "homePageController" , moduleName = Rout.ModuleType.JDMAN , methodName = "userNewAdd")
    public Result userNewAdd(@RequestBody Input input) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return csf.csfToResult(input);
    }
    /**
     * 新增留言
     * */
    @RequestMapping("/addNewMessage")
    @Rout(controllerName = "homePageController" , moduleName = Rout.ModuleType.JDMAN , methodName = "addNewMessage")
    public Result addNewMessage(@RequestBody Input input) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Result result = new Result();
        Map<String , Object> params = input.getParams();
        String leavingMessageContent = (String) params.get("leavingMessageContent");
        //检查留言内容是否为空
        if (leavingMessageContent == null || leavingMessageContent == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("留言内容不能为空");
            return result;
        }
        input.getParams().put("userAccount" , userInfo.getUserAccount());
        return csf.csfToResult(input);
    }
    /**
     * 查询留言
     * */
    @RequestMapping("/getMessage")
    @Rout(controllerName = "homePageController" , moduleName = Rout.ModuleType.JDMAN , methodName = "getMessage")
    public Result getMessage(@RequestBody Input input) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Result result = new Result();
        String start = (String) input.getParams().get("start");
        String limit = (String) input.getParams().get("limit");
        if (start == null || start == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("开始页不能为空");
            return result;
        }
        if (limit == null || limit == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("页码不能为空");
            return result;
        }
        return csf.csfToResult(input);
    }
    /**
     * 查看待办
     * */
    @RequestMapping("/getNeedDo")
    @Rout(controllerName = "homePageController" , moduleName = Rout.ModuleType.JDMAN , methodName = "getNeedDo")
    public Result getNeedDo(@RequestBody Input input) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Result result = new Result();
        String start = (String) input.getParams().get("start");
        String limit = (String) input.getParams().get("limit");
        if (start == null || start == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("开始页不能为空");
            return result;
        }
        if (limit == null || limit == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("页码不能为空");
            return result;
        }
        input.getParams().put("userAccount" , userInfo.getUserAccount());
        return csf.csfToResult(input);
    }
    /**
     * 通过待办
     * */
    @RequestMapping("/passNeedDo")
    @Rout(controllerName = "homePageController" , moduleName = Rout.ModuleType.JDMAN , methodName = "passNeedDo")
    public Result passNeedDo(@RequestBody Input input) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Result result = new Result();
        String needDoId = (String) input.getParams().get("needDoId");
        String needDoSource = (String) input.getParams().get("needDoSource");
        if (needDoId == null || needDoId == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("待办ID不允许为空");
            return result;
        }
        if (needDoSource == null || needDoSource == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("待办来源ID不允许为空");
            return result;
        }
        return csf.csfToResult(input);
    }
    /**
     * 驳回待办
     * */
    @RequestMapping("/rejectNeedDo")
    @Rout(controllerName = "homePageController" , moduleName = Rout.ModuleType.JDMAN , methodName = "rejectNeedDo")
    public Result rejectNeedDo(@RequestBody Input input) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Result result = new Result();
        String needDoId = (String) input.getParams().get("needDoId");
        String rejectReason = (String) input.getParams().get("rejectReason");
        if (needDoId == null || needDoId == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("待办ID不允许为空");
            return result;
        }
        if (rejectReason == null || rejectReason == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("驳回原因不允许为空");
            return result;
        }
        if (rejectReason.length() > 45){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("驳回原因最大字数只能是45");
            return result;
        }
        return csf.csfToResult(input);
    }
}

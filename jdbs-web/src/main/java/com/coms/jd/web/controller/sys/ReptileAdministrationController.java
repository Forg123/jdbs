package com.coms.jd.web.controller.sys;

import com.coms.jd.beans.code.ResultCode;
import com.coms.jd.beans.code.RoleCode;
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
import java.util.Map;

/**
 * 爬虫关键字管理页面
 * */
@RestController
@RequestMapping("/reptileAdministration")
public class ReptileAdministrationController {
    @Autowired
    private CsfUtilsCall csf;
    @Autowired
    private UserInfo userInfo;
    /**
     * 按条件查看爬虫关键字
     * */
    @CrossOrigin
    @RequestMapping("/getReptileKeyWords")
    @Rout(controllerName = "reptileAdministrationController", moduleName = Rout.ModuleType.JDMAN, methodName = "getReptileKeyWords")
    public Result getReptileKeyWords(@RequestBody Input input) throws ClassNotFoundException,
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
     * 查看详情
     * */
    @CrossOrigin
    @RequestMapping("/getReptileKeyWordsDetail")
    @Rout(controllerName = "reptileAdministrationController", moduleName = Rout.ModuleType.JDMAN, methodName = "getReptileKeyWordsDetail")
    public Result getReptileKeyWordsDetail(@RequestBody Input input) throws ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Result result = new Result();
        String reptileDicId = (String) input.getParams().get("reptileDicId");
        if (reptileDicId == null || reptileDicId == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("查看关键字详情，需要关键字ID");
            return result;
        }
        return csf.csfToResult(input);
    }
    /**
     * 修改爬虫关键字
     * */
    @CrossOrigin
    @RequestMapping("/updateReptileKeywords")
    @Rout(controllerName = "reptileAdministrationController", moduleName = Rout.ModuleType.JDMAN, methodName = "updateReptileKeywords")
    public Result updateReptileKeywords(@RequestBody Input input) throws ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Result result = new Result();
        String roleId = userInfo.getRoles().get(0);
        String reptileDicStatus = (String) input.getParams().get("reptileDicStatus");
        String userAccount = userInfo.getUserAccount();
        String approvalAccount = (String) input.getParams().get("approvalAccount");
        if (reptileDicStatus == null || reptileDicStatus == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("修改爬虫关键字，操作类型不能为空");
            return result;
        }
        if (!(RoleCode.SUPER_ADMINISTRATOR_ROLE_ID.equals(roleId))){
            //需要检查是否选择了审批人
            if (approvalAccount == null || approvalAccount == ""){
                result.setReturnCode(ResultCode.ERROR);
                result.setReturnMessage("您当前角色不是超级管理员，需要选择一个审批人");
                return result;
            }
        }
        input.getParams().put("roleId" , roleId);
        input.getParams().put("userAccount" , userAccount);
        return csf.csfToResult(input);
    }
    /**
     * 创建爬虫关键字
     * */
    @CrossOrigin
    @RequestMapping("/createReptileKeywords")
    @Rout(controllerName = "reptileAdministrationController", moduleName = Rout.ModuleType.JDMAN, methodName = "createReptileKeywords")
    public Result createReptileKeywords(@RequestBody Input input) throws ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Result result = new Result();
        Map<String , Object> param = input.getParams();
        String roleId = userInfo.getRoles().get(0);
        String userAccount = userInfo.getUserAccount();
        String approvalAccount = (String) param.get("approvalAccount");
        String keyWords = (String) param.get("keyWords");
        String reptileDicTypNumber = (String) param.get("reptileDicTypNumber");
        String reptileDicTyp = (String) param.get("reptileDicTyp");
        String reptileDicNumber = (String) param.get("reptileDicNumber");
        String url = (String) param.get("url");
        if (!(RoleCode.SUPER_ADMINISTRATOR_ROLE_ID.equals(roleId))){
            //需要检查是否选择了审批人
            if (approvalAccount == null || approvalAccount == ""){
                result.setReturnCode(ResultCode.ERROR);
                result.setReturnMessage("您当前角色不是超级管理员，需要选择一个审批人");
                return result;
            }
            input.getParams().put("userAccount" , userAccount);
        }
        if (keyWords == null || keyWords == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("新建关键字，关键字不能为空");
            return result;
        }
        if (reptileDicTypNumber == null || reptileDicTypNumber == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("新建关键字，关键字类型编码不能为空");
            return result;
        }
        if (reptileDicTyp == null || reptileDicTyp == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("新建关键字，关键字类型不能为空");
            return result;
        }
        if (reptileDicNumber == null || reptileDicNumber == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("新建关键字，爬取页数不能为空");
            return result;
        }
        if (url == null || url == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("新建关键字，爬取地址不能为空");
            return result;
        }
        input.getParams().put("roleId" , roleId);
        input.getParams().put("userAccount" , userAccount);
        return csf.csfToResult(input);
    }
}

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
 * 用户注册发送验证码
 * */
@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private CsfUtilsCall csf;
    @Autowired
    private UserInfo userInfo;
    /**
     * 发送验证码
     * */
    @CrossOrigin
    @RequestMapping("/sendVerificationCode")
    @Rout(controllerName = "registerController" , moduleName = Rout.ModuleType.JDMAN , methodName = "sendVerificationCode")
    public Result sendVerificationCode(@RequestBody Input input) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        //
        Result result = new Result();
        String email = (String) input.getParams().get("email");
        if (email == null || "".equals(email)){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("请输入正确的邮箱");
            return result;
        }
        result = csf.csfToResult(input);
        return result;
    }
    /**
     * 检验是否能注册成功
     * */
    @CrossOrigin
    @RequestMapping("/checkOutCode")
    @Rout(controllerName = "registerController" , moduleName = Rout.ModuleType.JDMAN , methodName = "checkOutCode")
    public Result checkOutCode(@RequestBody Input input) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        Result result = new Result();
        String code = (String) input.getParams().get("code");
        String email = (String) input.getParams().get("email");
        String password = (String) input.getParams().get("password");
        if (code == null || code == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("请正确的输入验证码");
            return result;
        }
        if (email == null || email == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("请正确的输入邮箱");
            return result;
        }
        int passwordLength = password.length();
        if (password == null || password == "" || passwordLength < 6 || passwordLength > 10){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("密码位数只能在6到10之间");
            return result;
        }
        return csf.csfToResult(input);
    }
}

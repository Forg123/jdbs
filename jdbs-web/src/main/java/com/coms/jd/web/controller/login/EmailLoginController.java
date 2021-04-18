package com.coms.jd.web.controller.login;

import com.coms.jd.beans.code.ResultCode;
import com.coms.jd.utils.Input;
import com.coms.jd.utils.Result;
import com.coms.jd.utils.Rout;
import com.coms.jd.utils.UserInfo;
import com.coms.jd.utils.jwt.JwtTokenUtil;
import com.coms.jd.web.csf.CsfUtilsCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户使用邮件登陆
 * */
@RestController
@RequestMapping("/login")
public class EmailLoginController {
    @Autowired
    private CsfUtilsCall csf;
    @Autowired
    private UserInfo userInfo;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    /**
     * 邮件登陆发送邮件
     * */
    @CrossOrigin
    @RequestMapping("/sendEmail")
    @Rout(controllerName = "emailLoginController", moduleName = Rout.ModuleType.JDMAN, methodName = "sendEmail")
    public Result sendEmail(@RequestBody Input input) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        Result result = new Result();
        String email = (String) input.getParams().get("email");
        if (email == null || email == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("用户登陆，邮箱不能为空");
            return result;
        }
        return csf.csfToResult(input);
    }
    /**
     * 用户邮箱登陆，检查验证码
     * */
    @CrossOrigin
    @RequestMapping("/doLogin")
    @Rout(controllerName = "emailLoginController", moduleName = Rout.ModuleType.JDMAN, methodName = "doLogin")
    public Result doLogin(@RequestBody Input input) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        Result result = new Result();
        Map<String , Object> params = input.getParams();
        String email = (String) params.get("email");
        String code = (String) params.get("code");
        if (email == null || email == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("正在使用邮箱登陆，邮箱不能为空");
            return result;
        }
        if (code == null || code == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("正在使用邮箱登陆，验证码不能为空");
            return result;
        }
        Result ret = csf.csfToResult(input);
        if (!ResultCode.SUCCESS.equals(ret.getReturnCode())){
            return ret;
        }
        if (ResultCode.SUCCESS.equals(ret.getReturnCode())){
            //1、生成token
            //2、重新组装返回数据给前端
            Map<String , Object> createToken = new HashMap<>();
            String userAccount = (String) ret.getBean().get("userAccount");
            int roleLevel = (int) ret.getBean().get("roleLevel");
            String roleId = (String) ret.getBean().get("roleId");
            List<String> userRoles = Arrays.asList(roleId.split(","));
            createToken.put("userAccount" , userAccount);
            createToken.put("roleLevel" , roleLevel);
            createToken.put("userRoles" , userRoles);
            Map<String , Object> retMap = new HashMap<>();
            retMap.put("token" , jwtTokenUtil.createTokenByUser(createToken));
            result.setBean(retMap);
            result.setReturnCode(ResultCode.LOGIN_SUCCESS);
            result.setReturnMessage("登陆成功");
            return result;
        }
        else {
            result.setReturnCode(ResultCode.LOGIN_FILE);
            result.setReturnMessage("登陆失败");
            return result;
        }
    }
}

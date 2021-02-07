package com.coms.jd.web.controller.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.coms.jd.service.sys.GetUser;
import com.coms.jd.utils.Input;
import com.coms.jd.utils.Result;
import com.coms.jd.utils.Rout;
import com.coms.jd.web.csf.CsfUtilsCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class SysController {
    @Autowired
    private CsfUtilsCall csf;
    @Reference
    private GetUser getUser;
    @RequestMapping("/sys")
    public String sysControoler(@RequestBody Input input){
        Map<String , Object> params = getUser.getUserIInfo();
        return "sys接口，属于bs的controller层:" + params.toString();
    }
    @RequestMapping("/user")
    public String userController(){
        Map<String , Object> params = getUser.getUserIInfo();
        return "user接口，属于bs的controller层,需要user权限才能访问";
    }
    @RequestMapping("/csfUtils")
    @Rout(controllerName = "sysManController" , moduleName = Rout.ModuleType.JDMAN , methodName = "sysMan")
    public Result sysCsfUtil(@RequestBody Input input) throws ClassNotFoundException, NoSuchMethodException{
        /**
         * csf接口调用
         * */
        Result result = csf.csfToResult(input);
        return result;
        }
}

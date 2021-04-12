package com.coms.jd.web.csf;

import com.alibaba.dubbo.config.annotation.Reference;
import com.coms.jd.beans.code.ResultCode;
import com.coms.jd.utils.Input;
import com.coms.jd.utils.Result;
import com.coms.jd.utils.Rout;
import com.coms.jd.utils.csf.CsfUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Component
public class CsfUtilsCall {
    @Reference(group = "JDMAN" , version = "v0.1")
    @Qualifier("csfUtils")
    private CsfUtils csfUtilsMan;
    @Reference(group = "JDBASE" , version = "v0.1")
    @Qualifier("csfUtils")
    private CsfUtils csfUtilsBase;
    @Reference(group = "JDAPI" , version = "v0.1")
    @Qualifier("csfUtils")
    private CsfUtils csfUtilsApi;
    public Result csfToResult(Input input) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Result result = new Result();
        //获取调用者的类名
        String classname = new Exception().getStackTrace()[1].getClassName();
        //获取调用者的方法名
        String method_name = new Exception().getStackTrace()[1].getMethodName();
        /**
         * 获取注解的值
         * */
        Class<?> clazz = Class.forName(classname);
        /**
         * 获取这个类的指定方法的注解
         * */
        Method method = clazz.getDeclaredMethod(method_name , Input.class);
        Rout rout = method.getDeclaredAnnotation(Rout.class);
        String controllerName = rout.controllerName();
        String methodName = rout.methodName();
        String moduleName =  rout.moduleName().name();
        input.setControllerName(controllerName);
        input.setMethodName(methodName);
        if (Rout.ModuleType.JDMAN.name().equals(moduleName)){
            return csfUtilsMan.csfCallToResult(input);
        }
        if (Rout.ModuleType.JDBASE.name().equals(moduleName)){
            return csfUtilsBase.csfCallToResult(input);
        }
        if (Rout.ModuleType.JDAPI.name().equals(moduleName)){
            return csfUtilsApi.csfCallToResult(input);
        }
        //打印日志
        result.setReturnCode(ResultCode.ERROR);
        result.setReturnMessage("路由注解配置有误，请联系管理员处理");
        return result;
    }
}

package com.coms.jd.web.csf;

import com.alibaba.dubbo.config.annotation.Reference;
import com.coms.jd.utils.Input;
import com.coms.jd.utils.Result;
import com.coms.jd.utils.Rout;
import com.coms.jd.utils.csf.CsfUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class CsfUtilsCall {
    @Reference
    private CsfUtils csfUtils;
    public Result csfToResult(Input input) throws ClassNotFoundException, NoSuchMethodException {
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
        return csfUtils.csfCallToResult(input);
    }
}

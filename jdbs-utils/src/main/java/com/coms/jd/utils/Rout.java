package com.coms.jd.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Rout {
    enum ModuleType {JDMAN , JDAPI , JDBASE}
    String controllerName();
    String methodName();
    ModuleType moduleName();
}

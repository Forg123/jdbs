package com.coms.jd.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Input implements Serializable {
    private Map<String , Object> params;
    private String controllerName;
    private String methodName;
    private String module;
    private List<Map<String , Object>> beans;
    public Map<String, Object> getParams() {
        return params;
    }
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
    public String getControllerName() {
        return controllerName;
    }
    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }
    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    public String getModule() {
        return module;
    }
    public void setModule(String module) {
        this.module = module;
    }
    public List<Map<String, Object>> getBeans() {
        return beans;
    }
    public void setBeans(List<Map<String, Object>> beans) {
        this.beans = beans;
    }
}

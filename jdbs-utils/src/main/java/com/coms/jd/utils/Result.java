package com.coms.jd.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设置返回结果集
 * */
public class Result implements Serializable {
    List<Map<String , Object>> beans;
    Map<String , Object> bean;
    String returnCode;
    String returnMessage;
    public Map<String, Object> retData(){
        Map<String , Object> params = new HashMap<>();
        params.put("bean" , this.bean);
        params.put("beans" , this.beans);
        params.put("returnCode" , this.returnCode);
        params.put("returnMessage" , this.returnMessage);
        return params;
    }
    public List<Map<String, Object>> getBeans() {
        return beans;
    }

    public void setBeans(List<Map<String, Object>> beans) {
        this.beans = beans;
    }

    public Map<String, Object> getBean() {
        return bean;
    }

    public void setBean(Map<String, Object> bean) {
        this.bean = bean;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }
}

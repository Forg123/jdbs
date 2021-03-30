package com.coms.jd.beans.code;

/**
 * 返回值编码
 * */
public interface ResultCode {
    /**
     * 登陆成功返回值
     * */
    String LOGIN_SUCCESS = "4000";
    /**
     * 登陆失败返回值
     * */
    String LOGIN_FILE = "4041";
    /**
     * 已登陆用户访问无权限资源
     * */
    String LOGIN_NO_POWER = "4042";
    /**
     * 未登陆用户访问无权限资源
     * */
    String NO_LOGIN_NO_POWER = "4043";
    /**
     * 登出成功
     * */
    String LOGINOUT_SUCCESS = "4002";
    /**
     * 接口访问成功
     * */
    String SUCCESS = "0";
    /**
     * 接口访问失败
     * */
    String ERROR = "-9999";
}

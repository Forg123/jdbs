package com.coms.jd.web.controller.sys;

import com.coms.jd.beans.code.ResultCode;
import com.coms.jd.utils.Input;
import com.coms.jd.utils.Result;
import com.coms.jd.utils.Rout;
import com.coms.jd.utils.UserInfo;
import com.coms.jd.web.csf.CsfUtilsCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

/**
 * 商品查询管理模块
 * */
@RestController
@RequestMapping("/goodsInfo")
public class GoodsInfoController {
    @Autowired
    private CsfUtilsCall csf;
    @Autowired
    private UserInfo userInfo;
    /**
     * 查询商品列表
     * */
    @RequestMapping("/getGoodsList")
    @Rout(controllerName = "goodsInfoController" , moduleName = Rout.ModuleType.JDBASE , methodName = "getGoodsList")
    public Result getGoodsList(@RequestBody Input input) throws ClassNotFoundException,
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
        return csf.csfToResult(input);
    }
    /**
     * 查询商品详情和价格涨幅情况
     * */
    @RequestMapping("/getGoodsDetail")
    @Rout(controllerName = "goodsInfoController" , moduleName = Rout.ModuleType.JDBASE , methodName = "getGoodsDetail")
    public Result getGoodsDetail(@RequestBody Input input) throws ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Result result = new Result();
        String goodsId = (String) input.getParams().get("goodsId");
        if (goodsId == null || goodsId == ""){
            result.setReturnCode(ResultCode.ERROR);
            result.setReturnMessage("查询商品详情，商品ID不允许为空");
            return result;
        }
        return csf.csfToResult(input);
    }
}

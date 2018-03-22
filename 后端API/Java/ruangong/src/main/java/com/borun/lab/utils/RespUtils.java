package com.borun.lab.utils;

import com.alibaba.fastjson.JSONObject;
import com.borun.lab.utils.bean.RespStatus;

/**
 * 注意   obj需为pojo或pojo的集合形式
 * msg和info的区别:  info为状态码解释，无需自己写，msg为可携带参数，可作为参数传入
 */
public class RespUtils {
    private RespUtils() {
    }

    public static JSONObject createSucResp() {
        return createResp(RespStatus.SUCCESS);
    }

    public static JSONObject createSucResp(Object o) {
        return createResp(RespStatus.SUCCESS, o);
    }

    public static JSONObject createSucResp(String msg) {
        return createResp(RespStatus.SUCCESS, msg);
    }

    public static JSONObject createSucResp(String msg, Object o) {
        return createResp(RespStatus.SUCCESS, msg, o);
    }

    public static JSONObject createResp(RespStatus status) {
        return createResp(status, null);
    }

    public static JSONObject createResp(RespStatus status, Object o) {
        return createResp(status, null, o);
    }

    public static JSONObject createResp(RespStatus status, String msg) {
        return createResp(status, msg, null);
    }

    public static JSONObject createFailResp(){
        return createResp(RespStatus.FAILURE,null,null);
    }

    public static JSONObject createFailResp(String msg){
        return createResp(RespStatus.FAILURE,msg,null);
    }

    /**
     * 创建一个基本的响应
     */
    public static JSONObject createResp(RespStatus status, String msg, Object data) {

        JSONObject resp = new JSONObject();
        resp.put("status", status.getCode());
        resp.put("info", status.getDesc());
        if (msg != null) {
            resp.put("msg", msg);
        }
        if (data != null) {
            resp.put("data", data);
        }
        return resp;
    }

}

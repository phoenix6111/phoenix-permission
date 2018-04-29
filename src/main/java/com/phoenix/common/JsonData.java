package com.phoenix.common;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * User: sheng
 * Date: 2018-04-25 10:53
 * Description: 请求返回Json格式的数据
 */
@Getter
@Setter
public class JsonData {

    //请求是否成功的标志
    private boolean ret;

    //返回的附加提示信息，
    private String msg;

    //请求返回的数据
    private Object data;

    public JsonData(boolean ret) {
        this.ret = ret;
    }

    public JsonData(boolean ret, String msg) {
        this.ret = ret;
        this.msg = msg;
    }

    public JsonData(boolean ret, String msg, Object data) {
        this.ret = ret;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回失败信息，仅携带成功标志ret
     * @return
     */
    public static JsonData fail() {
        return new JsonData(false);
    }

    /**
     * 返回失败信息，仅携带ret和msg
     * @param msg
     * @return
     */
    public static JsonData fail(String msg) {
        return new JsonData(false,msg);
    }

    /**
     * 返回成功信息，仅携带成功标志ret
     * @return
     */
    public static JsonData success() {
        return new JsonData(true);
    }

    /**
     * 返回成功信息，携带ret和msg
     * @param msg
     * @return
     */
    public static JsonData success(String msg) {
        return new JsonData(true,msg);
    }

    /**
     * 返回成功信息，携带ret,msg,data
     * @param data
     * @param msg
     * @return
     */
    public static JsonData success(Object data,String msg) {
        return new JsonData(true,msg,data);
    }

    public Map<String,Object> toMap() {
        Map<String,Object> map = new HashMap<>();
        map.put("ret",ret);
        map.put("msg",msg);
        map.put("data",data);

        return map;
    }


}

package com.lzx.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ezio
 * @date 2020/10/4 21:29
 */
public class Msg {
    /**
     * 状态码 100成功 200失败
     */
    private int code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 用户要返回给浏览器的数据
     */
    private Map<String, Object> extend = new HashMap<>();

    public static Msg success() {
        Msg result = new Msg();
        result.setCode(100);
        result.setMsg("处理成功");
        return result;
    }

    public static Msg fail() {
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("处理失败");
        return result;
    }

    /**
     * 实现链式调用
     */
    public Msg add(String key, Object value) {
        this.getExtend().put(key, value);
        return this;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

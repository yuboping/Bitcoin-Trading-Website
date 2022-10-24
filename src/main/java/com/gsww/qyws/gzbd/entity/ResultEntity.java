package com.gsww.qyws.gzbd.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 类Result的功能描述:
 * 返回数据实体类
 *
 * @auther zhangjy
 * @date 2018-03-20 11:57:50
 */
public class ResultEntity extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public ResultEntity() {
        put("code", Constant.RESULT.CODE_YES.getValue());
        put("msg", Constant.RESULT.MSG_YES.getValue());

    }

    public ResultEntity(String code, String msg) {
        put("code", code);
        put("msg", msg);
    }

    public static ResultEntity error() {
        return new ResultEntity(Constant.RESULT.CODE_NO.getValue(), Constant.RESULT.MSG_NO.getValue());
    }

    public static ResultEntity error(String msg) {
        return error(Constant.RESULT.CODE_NO.getValue(), msg);
    }

    public static ResultEntity error(String code, String msg) {
        ResultEntity r = new ResultEntity();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static ResultEntity ok(String msg) {
        ResultEntity r = new ResultEntity();
        r.put("msg", msg);
        return r;
    }

    public static ResultEntity ok(Map<String, Object> map) {
        ResultEntity r = new ResultEntity();
        r.putAll(map);
        return r;
    }

    public static ResultEntity ok() {
        return new ResultEntity(Constant.RESULT.CODE_YES.getValue(), Constant.RESULT.MSG_YES.getValue());
    }

    public ResultEntity put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
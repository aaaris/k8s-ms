package com.aurora.k8sms.common.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;
import java.io.Serializable;

@Data
public class R implements Serializable {

    // 状态码
    private Integer status;
    // 返回信息
    private String msg;
    // 返回数据，因为返回数据是不确定类型，所以只能考虑Object或者泛型
    private Object data;

    // 全部约束使用方法区执行和返回，不允许到到外部去new
    private R() {

    }

    public static R success(Object data) {
        return restResult(data, HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
    }

    public static R success(Object data, String msg) {
        return restResult(data, HttpStatus.OK.value(), msg);
    }

    public static R error(HttpStatus status) {
        return restResult(null, status.value(), status.getReasonPhrase());
    }

    public static R error(int status,String msg) {
        return restResult(null,status,msg);
    }

    private static R restResult(Object data, Integer status, String msg) {
        R r = new R();
        r.setStatus(status);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}


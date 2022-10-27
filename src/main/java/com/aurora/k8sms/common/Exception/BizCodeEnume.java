package com.aurora.k8sms.common.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BizCodeEnume {
    UNKOWN_EXCEPTION(10000,"系统未知异常"),
    VAILD_EXCEPTION(10001,"参数格式校验失败"),
    API_EXCEPTION(4001,"api故障");

    private int code;
    private String msg;

}

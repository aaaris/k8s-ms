package com.aurora.k8sms.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ServiceDto {
    //服务名称
    private String name;
    //pod选择器标签
    private Map<String,String> labels;
    //集群外服务访问端口
    int nodePort;
    //请求转发端口
    int targetPort;
}

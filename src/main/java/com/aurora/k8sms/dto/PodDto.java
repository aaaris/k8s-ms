package com.aurora.k8sms.dto;

import lombok.Data;

@Data
public class PodDto {
    //节点名（虚拟机hostname
    private String nodeName;
    //镜像名
    private String imageName;
}

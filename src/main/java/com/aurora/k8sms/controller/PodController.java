package com.aurora.k8sms.controller;

import com.aurora.k8sms.common.utils.R;
import com.aurora.k8sms.dto.PodDto;
import com.aurora.k8sms.service.PodService;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Pod;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/pod")
public class PodController {

    @Autowired
    private PodService podService;


    @ApiOperation(value = "查询 pod", notes = "查询所有命名空间下的 pods")
    @GetMapping("")
    public R getAllPods() throws ApiException, IOException {
        log.info("getAllPods");
        List<V1Pod> pods = podService.list();
        return R.success(pods.stream().map(value -> value.getMetadata().getNamespace()
                + ":" + value.getMetadata().getName()).collect(Collectors.toList()));
    }


    @ApiOperation(value = "新建 pod", notes = "根据 container 在指定 node 创建 pod")
    @PostMapping("")
    public R addPodByName(@RequestParam(defaultValue = "default", required = false) String namespace,
                          @RequestBody PodDto podDto) throws ApiException {
        V1Pod pod = podService.create(podDto, namespace);
        return  R.success(pod.getMetadata());
    }
}

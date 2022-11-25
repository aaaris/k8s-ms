package com.aurora.k8sms.controller;

import com.aurora.k8sms.common.utils.R;
import com.aurora.k8sms.dto.ServiceDto;
import com.aurora.k8sms.service.ServiceService;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Service;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/api/v1/service")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @ApiOperation(value = "查询 service", notes = "查询所有 service")
    @GetMapping("")
    public R list() throws ApiException {
        List<V1Service> serviceList = serviceService.list();
        return R.success(serviceList.stream().map(item -> item.getMetadata().getNamespace() + ":" + item.getMetadata().getName()).collect(Collectors.toList()));
    }

    @ApiOperation(value = "新建 service", notes = "根据 yaml 创建 service")
    @PostMapping("/yaml")
    public R create(@RequestParam(required = false, defaultValue = "default") String namespace, @RequestBody String yamlSrc) throws ApiException {
        return R.success(serviceService.create(namespace, yamlSrc).getMetadata());
    }

    @ApiOperation(value = "新建 service", notes = "根据 pod label 创建 service")
    @PostMapping("/podLabel")
    public R createByName(@RequestParam(required = false, defaultValue = "default") String namespace, @RequestBody ServiceDto serviceDto) throws ApiException {
        return R.success(serviceService.createByName(namespace, serviceDto).getMetadata());
    }


}

package com.aurora.k8sms.controller;

import com.aurora.k8sms.common.utils.R;
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


    // TODO: 测试接口
    @ApiOperation(value = "查询 service", notes = "根据namespace 查询服务")
    @GetMapping("/{namespace}")
    public R listByNamespace(@PathVariable String namespace) throws ApiException {
        List<V1Service> serviceList = serviceService.listByNamespace(namespace);
        return R.success(serviceList.stream().map(item -> item.getMetadata().getNamespace() + ":" + item.getMetadata().getName()).collect(Collectors.toList()));
    }


    // TODO: 测试接口
    @ApiOperation(value = "查询 service", notes = "根据name 查询服务")
    @GetMapping("/{namespace}/{name}")
    public R listByNamespace(@PathVariable String namespace, @PathVariable String name) throws ApiException {
        V1Service v1Service = serviceService.getByName(namespace, name);
        return R.success(v1Service.getMetadata());
    }

    @ApiOperation(value = "新建 service", notes = "根据 yaml 创建 service")
    @PostMapping("/yaml")
    public R create(@RequestParam(required = false, defaultValue = "default") String namespace,
                    @RequestBody String yamlSrc) throws ApiException {
        return R.success(serviceService.create(namespace, yamlSrc).getMetadata());
    }

    // TODO: 测试接口
    @ApiOperation(value = "修改 service", notes = "根据 yaml 修改 service")
    @PatchMapping("/yaml")
    public R patch(@RequestParam(defaultValue = "default") String namespace,
                   @RequestParam String name,
                   @RequestBody String yamlSrc) throws ApiException {
        return R.success(serviceService.patch(namespace, name, yamlSrc));
    }
}

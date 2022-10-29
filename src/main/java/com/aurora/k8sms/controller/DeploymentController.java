package com.aurora.k8sms.controller;

import com.aurora.k8sms.common.utils.R;
import com.aurora.k8sms.service.DeploymentService;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/api/v1/deployment")
public class DeploymentController {

    @Autowired
    private DeploymentService deploymentService;


    @ApiOperation(value = "查询 deployment", notes = "查询所有 deployment")
    @GetMapping("")
    public R list() throws ApiException {
        List<V1Deployment> v1DeploymentList = deploymentService.list();
        return R.success(v1DeploymentList.stream().map(item -> item.getMetadata().getNamespace() + ":" + item.getMetadata().getName()).collect(Collectors.toList()));
    }

    // TODO: 测试接口
    @ApiOperation(value = "查询 deployment", notes = "根据 namespace 查询 deployment")
    @GetMapping("/{namespace}")
    public R listByNamespace(@PathVariable String namespace) throws ApiException {
        List<V1Deployment> v1DeploymentList = deploymentService.listByNamespace(namespace);
        return R.success(v1DeploymentList.stream().map(item -> item.getMetadata().getName()).collect(Collectors.toList()));
    }

    // TODO: 测试接口
    @ApiOperation(value = "查询 deployment", notes = "根据 name 查询 deployment")
    @GetMapping("/{namespace}/{name}")
    public R readByName(@PathVariable String namespace, @PathVariable String name) throws ApiException {
        return R.success(deploymentService.readByName(namespace,name));
    }

    @ApiOperation(value = "新增 deployment", notes = "根据 yaml 部署 deployment")
    @PostMapping("/yaml")
    public R createByYaml(@RequestParam(defaultValue = "default",required = false) String namespace, @RequestBody String yamlSrc) throws ApiException {
        log.info("namespace:{}\n", namespace);
        V1Deployment v1Deployment = deploymentService.create(namespace, yamlSrc);
        return R.success(v1Deployment.getMetadata());
    }

    // TODO: 测试接口
    @ApiOperation(value = "修改 deployment", notes = "根据 yaml 修改 deployment")
    @PatchMapping("/yaml/{namespace}/{name}")
    public R patch(@PathVariable String namespace, @PathVariable String name, @RequestBody String yamlSrc) throws ApiException {
        log.info("namespace:{}, name:{}", namespace, name);
        return R.success(deploymentService.patch(namespace, name, yamlSrc));
    }

}

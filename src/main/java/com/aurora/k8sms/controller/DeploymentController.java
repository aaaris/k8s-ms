package com.aurora.k8sms.controller;

import com.aurora.k8sms.common.utils.R;
import com.aurora.k8sms.dto.DeploymentDto;
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

    @ApiOperation(value = "新增 deployment", notes = "根据 container 在指定 node 部署 deployment")
    @PostMapping("/container")
    public R createByContainerName(@RequestParam(defaultValue = "default",required = false) String namespace,
                                   @RequestBody DeploymentDto deploymentDto) throws ApiException {
        V1Deployment v1Deployment = deploymentService.createByName(namespace, deploymentDto);
        return R.success(v1Deployment.getMetadata());
    }


    @ApiOperation(value = "新增 deployment", notes = "根据 yaml 部署 deployment")
    @PostMapping("/yaml")
    public R createByYaml(@RequestParam(defaultValue = "default",required = false) String namespace, @RequestBody String yamlSrc) throws ApiException {
        V1Deployment v1Deployment = deploymentService.create(namespace, yamlSrc);
        return R.success(v1Deployment.getMetadata());
    }


}

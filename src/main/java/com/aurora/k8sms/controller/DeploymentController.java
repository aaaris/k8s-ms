package com.aurora.k8sms.controller;

import com.aurora.k8sms.common.utils.R;
import com.aurora.k8sms.service.DeploymentService;
import io.kubernetes.client.custom.V1Patch;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Deployment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/deployment")
public class DeploymentController {

    @Autowired
    private DeploymentService deploymentService;

    @PatchMapping("/{namespace}/{name}")
    public R patch(@PathVariable String namespace,@PathVariable String name, @RequestBody String v1PatchStr) throws ApiException {

        log.info("namespace:{},name:{}\n{}",namespace,name,v1PatchStr);
        V1Deployment v1Deployment = deploymentService.create(namespace,name, v1PatchStr);
        return R.success(v1Deployment);
        //return R.success("");
    }

}

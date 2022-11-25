package com.aurora.k8sms.service;

import com.aurora.k8sms.dto.DeploymentDto;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Deployment;

import java.util.List;

public interface DeploymentService {
    V1Deployment create(String namespace, String yamlSrc) throws ApiException;

    List<V1Deployment> list() throws ApiException;

    V1Deployment createByName(String namespace, DeploymentDto deploymentDto) throws ApiException;
}

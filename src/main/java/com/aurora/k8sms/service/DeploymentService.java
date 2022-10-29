package com.aurora.k8sms.service;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Deployment;

public interface DeploymentService {
    V1Deployment create(String namespace, String name, String v1Patch) throws ApiException;
}

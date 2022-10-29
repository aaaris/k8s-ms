package com.aurora.k8sms.service.Impl;

import com.aurora.k8sms.service.DeploymentService;
import io.kubernetes.client.custom.V1Patch;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.models.V1Deployment;
import org.springframework.stereotype.Service;

@Service
public class DeploymentServiceImpl implements DeploymentService {

    @Override
    public V1Deployment create(String namespace, String name, String v1PatchStr) throws ApiException {
        AppsV1Api api = new AppsV1Api();
        V1Deployment v1Deployment = api.patchNamespacedDeployment(name, namespace, new V1Patch(v1PatchStr), null, null, null, null, null);
        return v1Deployment;
    }
}

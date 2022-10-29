package com.aurora.k8sms.service.Impl;

import com.aurora.k8sms.service.DeploymentService;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1DeploymentList;
import io.kubernetes.client.util.Yaml;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeploymentServiceImpl implements DeploymentService {

    @Override
    public V1Deployment create(String namespace, String yamlSrc) throws ApiException {
        AppsV1Api api = new AppsV1Api();
        V1Deployment deployment = Yaml.loadAs(yamlSrc,V1Deployment.class);
        V1Deployment v1Deployment = api.createNamespacedDeployment(namespace,
                deployment, null, null, null, null);
        return v1Deployment;
    }

    @Override
    public V1Deployment patch(String namespace, String name, String yamlSrc) {
        return null;
    }

    @Override
    public List<V1Deployment> list() throws ApiException {
        AppsV1Api api = new AppsV1Api();
        V1DeploymentList v1DeploymentList = api.listDeploymentForAllNamespaces(null, null, null, null, null, null, null, null, null, null);
        return v1DeploymentList.getItems();
    }
}

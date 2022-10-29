package com.aurora.k8sms.service;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Deployment;

import java.util.List;

public interface DeploymentService {
    V1Deployment create(String namespace, String yamlSrc) throws ApiException;

    V1Deployment patch(String namespace, String name, String yamlSrc) throws ApiException;

    List<V1Deployment> list() throws ApiException;

    List<V1Deployment> listByNamespace(String namespace) throws ApiException;

    V1Deployment readByName(String namespace, String name) throws ApiException;
}

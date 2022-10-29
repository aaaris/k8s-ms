package com.aurora.k8sms.service;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Service;

import java.util.List;

public interface ServiceService {
    List<V1Service> list() throws ApiException;
    List<V1Service> listByNamespace(String namespace) throws ApiException;

    V1Service create(String yamlSrc, String src) throws ApiException;

    V1Service getByName(String namespace, String name) throws ApiException;

    V1Service patch(String namespace,String name, String yamlSrc) throws ApiException;
}

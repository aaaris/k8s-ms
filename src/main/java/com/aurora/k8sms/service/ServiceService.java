package com.aurora.k8sms.service;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Service;

import java.util.List;

public interface ServiceService {
    List<V1Service> list() throws ApiException;

    V1Service create(String yamlSrc, String src) throws ApiException;
}

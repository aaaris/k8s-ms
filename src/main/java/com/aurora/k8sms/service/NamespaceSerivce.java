package com.aurora.k8sms.service;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Namespace;

import java.io.IOException;
import java.util.List;

public interface NamespaceSerivce {
    /**
     * list all namespace
     * @return
     */
    List<V1Namespace> list() throws IOException, ApiException;

    /**
     * create a new namespace
     * @param name
     * @return
     */
    V1Namespace create(String name) throws IOException, ApiException;
}
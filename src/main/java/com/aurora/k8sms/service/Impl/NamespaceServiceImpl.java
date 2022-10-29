package com.aurora.k8sms.service.Impl;

import com.aurora.k8sms.service.NamespaceService;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1NamespaceList;
import io.kubernetes.client.openapi.models.V1ObjectMeta;
import io.kubernetes.client.util.Config;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class NamespaceServiceImpl implements NamespaceService {


    @Override
    public List<V1Namespace> list() throws ApiException {
        CoreV1Api api = new CoreV1Api();
        V1NamespaceList v1NamespaceList = api.listNamespace(null, null, null, null, null, null, null, null, null, null);
        return v1NamespaceList.getItems();
    }

    @Override
    public V1Namespace create(String name) throws   ApiException {
        CoreV1Api api = new CoreV1Api();
        V1Namespace v1Namespace = new V1Namespace();
        v1Namespace.setMetadata(new V1ObjectMeta().name(name));
        V1Namespace namespace = api.createNamespace(v1Namespace, null, null, null, null);
        return namespace;
    }
}

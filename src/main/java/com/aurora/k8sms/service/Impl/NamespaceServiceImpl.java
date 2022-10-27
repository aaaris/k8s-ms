package com.aurora.k8sms.service.Impl;

import com.aurora.k8sms.config.kubeConfig;
import com.aurora.k8sms.service.NamespaceSerivce;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreApi;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1NamespaceList;
import io.kubernetes.client.openapi.models.V1ObjectMeta;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class NamespaceServiceImpl implements NamespaceSerivce {


    @Override
    public List<V1Namespace> list() throws IOException, ApiException {
        ApiClient client = kubeConfig.getApiClient();
        CoreV1Api api = new CoreV1Api(client);
        V1NamespaceList v1NamespaceList = api.listNamespace(null, null, null, null, null, null, null, null, null, null);
        return v1NamespaceList.getItems();
    }

    @Override
    public V1Namespace create(String name) throws IOException, ApiException {
        ApiClient client = kubeConfig.getApiClient();
        CoreV1Api api = new CoreV1Api(client);
        V1Namespace v1Namespace = new V1Namespace();
        v1Namespace.setMetadata (new V1ObjectMeta().name(name));
        V1Namespace namespace = api.createNamespace(v1Namespace, null, null, null, null);
        return namespace;
    }
}

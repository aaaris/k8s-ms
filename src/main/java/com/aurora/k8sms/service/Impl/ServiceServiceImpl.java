package com.aurora.k8sms.service.Impl;

import com.aurora.k8sms.service.ServiceService;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.openapi.models.V1ServiceList;
import io.kubernetes.client.util.Yaml;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Override
    public List<V1Service> list() throws ApiException {
        CoreV1Api api = new CoreV1Api();
        V1ServiceList v1ServiceList = api.listServiceForAllNamespaces(null, null, null, null, null, null, null, null, null, null);
        return v1ServiceList.getItems();
    }


    @Override
    public V1Service create(String namespace, String yamlSrc) throws ApiException {
        CoreV1Api api = new CoreV1Api();
        V1Service v1Service = Yaml.loadAs(yamlSrc, V1Service.class);
        if (namespace.equals("") || namespace == null)
            namespace = "default";
        return api.createNamespacedService(namespace, v1Service, null, null, null, null);
    }
}

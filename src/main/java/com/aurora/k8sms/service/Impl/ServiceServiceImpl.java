package com.aurora.k8sms.service.Impl;

import com.aurora.k8sms.service.ServiceService;
import io.kubernetes.client.custom.V1Patch;
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
    public List<V1Service> listByNamespace(String namespace) throws ApiException {

        CoreV1Api api = new CoreV1Api();
        V1ServiceList v1ServiceList = api.listNamespacedService(namespace, null, null, null, null, null, null, null, null, null, null);
        return v1ServiceList.getItems();
    }
    @Override
    public V1Service create(String namespace, String yamlSrc) throws ApiException {
        CoreV1Api api = new CoreV1Api();
        V1Service v1Service = Yaml.loadAs(yamlSrc, V1Service.class);
        return api.createNamespacedService(namespace, v1Service, null, null, null, null);
    }

    @Override
    public V1Service getByName(String namespace, String name) throws ApiException {
        CoreV1Api api = new CoreV1Api();
        return api.readNamespacedService(name, namespace, null);
    }

    @Override
    public V1Service patch(String namespace, String name, String yamlSrc) throws ApiException {
        CoreV1Api api = new CoreV1Api();
        V1Patch v1Patch = Yaml.loadAs(yamlSrc, V1Patch.class);
        return api.patchNamespacedService(name, namespace, v1Patch, null, null, null, null, null);
    }
}

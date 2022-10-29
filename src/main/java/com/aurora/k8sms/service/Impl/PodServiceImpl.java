package com.aurora.k8sms.service.Impl;

import com.aurora.k8sms.dto.V1PodDto;
import com.aurora.k8sms.service.PodService;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1ObjectMeta;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PodServiceImpl implements PodService {


    @Override
    public List<V1Pod> list() throws ApiException {
        CoreV1Api api = new CoreV1Api();
        V1PodList podList = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null, null);
        return podList.getItems();
    }

    @Override
    public List<V1Pod> listByNamespace(String namespace) throws ApiException {
        CoreV1Api api = new CoreV1Api();
        V1PodList podList = api.listNamespacedPod(namespace, null, null, null, null, null, null, null, null, null, null);
        return podList.getItems();
    }

    @Override
    public V1Pod getByName(String name, String namespace) throws ApiException {
        CoreV1Api api = new CoreV1Api();
        V1Pod pod = api.readNamespacedPod(name, namespace, null);
        return pod;
    }

    @Override
    public V1Pod create(V1PodDto v1PodDto, String namespace) throws ApiException {
        CoreV1Api api = new CoreV1Api();
        V1Pod v1Pod = new V1Pod();
        v1Pod.setMetadata(new V1ObjectMeta().name(v1PodDto.getName()));
        return api.createNamespacedPod(namespace, v1Pod, null, null, null, null);
    }
}

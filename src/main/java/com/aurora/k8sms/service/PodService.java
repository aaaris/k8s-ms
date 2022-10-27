package com.aurora.k8sms.service;


import com.aurora.k8sms.dto.V1PodDto;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Pod;

import java.io.IOException;
import java.util.List;

public interface PodService {

    /**
     * 获取所有 pods
     * @return
     * @throws ApiException
     */
    List<V1Pod> list() throws ApiException, IOException;

    /**
     * 获取指定 namespace 的 pods
     *
     * @param namespace pods' namespace
     * @return a list including all pods
     */
    List<V1Pod> listByNamespace(String namespace) throws ApiException, IOException;

    /**
     * 获取指定 namespace 中指定 name 的 pod
     * @param namespace
     * @param name
     * @return a pod
     */
    V1Pod getByName(String namespace, String name) throws ApiException, IOException;

    /**
     * create a new pod in namespace
     * @param v1PodDto
     * @param namespace
     * @return
     */
    V1Pod create(V1PodDto v1PodDto, String namespace) throws IOException, ApiException;
}

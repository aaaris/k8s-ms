package com.aurora.k8sms.service;


import com.aurora.k8sms.dto.PodDto;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Pod;

import java.io.IOException;
import java.util.List;

public interface PodService {

    List<V1Pod> list() throws ApiException, IOException;

    V1Pod create(PodDto podDto, String namespace) throws ApiException;
}

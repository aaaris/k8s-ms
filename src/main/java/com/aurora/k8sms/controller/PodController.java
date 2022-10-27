package com.aurora.k8sms.controller;

import com.aurora.k8sms.common.utils.R;
import com.aurora.k8sms.dto.V1PodDto;
import com.aurora.k8sms.service.PodService;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Pod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/pods")
public class PodController {

    @Autowired
    private PodService podService;

    /**
     * 获取所有的 pods
     *
     * @return Respond
     */
    @GetMapping("")
    public R getAllPods() throws ApiException, IOException {
        log.info("getAllPods");
        List<V1Pod> pods = podService.list();
        return R.success(pods.stream().map(value -> value.getMetadata().getNamespace()
                + ":" + value.getMetadata().getName()).collect(Collectors.toList()));
    }


    /**
     * 获取当前 namespace 下所有的 pods
     *
     * @param namespace pod's namespace
     * @return Respond
     */
    @GetMapping("/{namespace}")
    public R getAllPodsByNamespace(@PathVariable String namespace) throws ApiException, IOException {
        log.info("getAllPodsByNamespace namespace={}", namespace);
        List<V1Pod> pods = podService.listByNamespace(namespace);
        return R.success(pods);
    }


    /**
     * 获取当前 namespace 下指定 name 的 pod
     *
     * @param namespace pod's namespace
     * @param name      pod's name
     * @return Respond
     */
    @GetMapping("/{namespace}/{name}")
    public R getPodByNameByNamespace(@PathVariable String namespace, @PathVariable String name) throws ApiException, IOException {
        log.info("getPodByNameByNamespace namespace={} name={}", namespace, name);
        V1Pod pod = podService.getByName(name, namespace);
        return R.success(pod);
    }

    /**
     * create a new pod in namespace
     * @param namespace
     * @return
     */
    @PostMapping("/{namespace}")
    public R addNamespacedPod(@PathVariable String namespace, @RequestBody V1PodDto v1PodDto) throws IOException, ApiException {
        // TODO: 测试接口
        V1Pod pod = podService.create(v1PodDto,namespace);
        return R.success(pod);
    }


}
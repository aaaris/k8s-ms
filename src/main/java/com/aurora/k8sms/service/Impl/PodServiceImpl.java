package com.aurora.k8sms.service.Impl;

import com.aurora.k8sms.dto.PodDto;
import com.aurora.k8sms.service.PodService;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PodServiceImpl implements PodService {
    @Override
    public List<V1Pod> list() throws ApiException {
        //获取 Kubernetes API
        CoreV1Api api = new CoreV1Api();
        //获取所有pod
        V1PodList podList = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null, null);
        //只返回List项，由于收集pod名
        return podList.getItems();
    }

    @Override
    public V1Pod create(PodDto podDto, String namespace) throws ApiException {
        //获取虚拟机节点hostname
        String nodeName = podDto.getNodeName();
        //获取镜像名
        String imageName = podDto.getImageName();
        //获取镜像格式名，格式实例 nginx:1.8 >> nginx-1-8
        String imageNameFormat = imageName.replaceAll("[:.]", "-");
        //使用镜像格式名生成容器名，格式示例 nginx:1.8 >> nginx-1-8-container
        String containerName = imageNameFormat + "-container";
        //使用镜像格式名生成DeploymentName
        String podName = imageNameFormat + "-pod";
        //获取 Kubernetes API
        CoreV1Api api = new CoreV1Api();
        //创建Pod实例，其实就是模拟yaml配置文件生成
        V1Pod v1Pod = new V1Pod()
                .metadata(new V1ObjectMeta()
                        //pod 名
                        .name(podName)
                        //添加pod标签
                        .putLabelsItem("app", imageNameFormat))
                .spec(new V1PodSpec()
                        //node选择器
                        .putNodeSelectorItem("kubernetes.io/hostname", nodeName)
                        //创建容器
                        .addContainersItem(new V1Container()
                                //容器名
                                .name(containerName)
                                //镜像名
                                .image(imageName)));
        return api.createNamespacedPod(namespace, v1Pod, null, null, null, null);
    }
}

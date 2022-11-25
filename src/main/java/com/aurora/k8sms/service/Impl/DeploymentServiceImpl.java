package com.aurora.k8sms.service.Impl;

import com.aurora.k8sms.dto.DeploymentDto;
import com.aurora.k8sms.service.DeploymentService;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.util.Yaml;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeploymentServiceImpl implements DeploymentService {

    @Override
    public V1Deployment create(String namespace, String yamlSrc) throws ApiException {
        //获取Kubernetes API
        AppsV1Api api = new AppsV1Api();
        //将传入的yaml转化为deployment实例
        V1Deployment deployment = Yaml.loadAs(yamlSrc, V1Deployment.class);
        return api.createNamespacedDeployment(namespace, deployment, null, null, null, null);
    }

    @Override
    public List<V1Deployment> list() throws ApiException {
        //获取Kubernetes API
        AppsV1Api api = new AppsV1Api();
        //获取所有Deployment
        V1DeploymentList v1DeploymentList = api.listDeploymentForAllNamespaces(null, null, null, null, null, null, null, null, null, null);
        //只返回List项，用于收集deployment名
        return v1DeploymentList.getItems();
    }

    @Override
    public V1Deployment createByName(String namespace, DeploymentDto deploymentDto) throws ApiException {
        //获取虚拟机节点hostname
        String nodeName = deploymentDto.getNodeName();
        //获取镜像名
        String imageName = deploymentDto.getImageName();
        //获取镜像格式名，格式实例 nginx:1.8 >> nginx-1-8
        String imageNameFormat = imageName.replaceAll("[:.]", "-");
        //使用镜像格式名生成容器名，格式示例 nginx:1.8 >> nginx-1-8-container
        String containerName = imageNameFormat + "-container";
        //使用镜像格式名生成DeploymentName
        String deploymentName = imageNameFormat + "-deployment";
        //获取Kubernetes API
        AppsV1Api api = new AppsV1Api();
        //创建Deployment实例，其实就是模拟yaml配置文件生成
        V1Deployment deployment = new V1Deployment()
                .apiVersion("apps/v1")
                .metadata(new V1ObjectMeta()
                        //deployment 名
                        .name(deploymentName)
                        //deployment 标签（默认使用镜像格式名
                        .putLabelsItem("app", imageNameFormat))
                .spec(new V1DeploymentSpec()
                        //选择的pod数量
                        .replicas(1)
                        //pod 选择器
                        .selector(new V1LabelSelector().putMatchLabelsItem("app", deploymentName))
                        //pod 模板
                        .template(new V1PodTemplateSpec()
                                .metadata(new V1ObjectMeta()
                                        //pod 标签设置
                                        .putLabelsItem("app", deploymentName))
                                .spec(new V1PodSpec()
                                        //node 选择器（使用 hostname 选择集群中的节点，即虚拟机）
                                        .putNodeSelectorItem("kubernetes.io/hostname", nodeName)
                                        //创建容器
                                        .addContainersItem(new V1Container()
                                                //容器名
                                                .name(containerName)
                                                //镜像名
                                                .image(imageName)))));
        return api.createNamespacedDeployment(namespace, deployment, null, null, null, null);
    }
}

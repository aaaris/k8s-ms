package com.aurora.k8sms.service.Impl;

import com.aurora.k8sms.dto.ServiceDto;
import com.aurora.k8sms.service.ServiceService;
import io.kubernetes.client.custom.IntOrString;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.util.Yaml;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Override
    public List<V1Service> list() throws ApiException {
        //获取Kubernetes API
        CoreV1Api api = new CoreV1Api();
        //获取所有Service
        V1ServiceList v1ServiceList = api.listServiceForAllNamespaces(null, null, null, null, null, null, null, null, null, null);
        //只返回List项，用于收集service名
        return v1ServiceList.getItems();
    }

    @Override
    public V1Service create(String namespace, String yamlSrc) throws ApiException {
        //获取 Kubernetes API
        CoreV1Api api = new CoreV1Api();
        //将传入的yaml转化为Service实例
        V1Service v1Service = Yaml.loadAs(yamlSrc, V1Service.class);
        return api.createNamespacedService(namespace, v1Service, null, null, null, null);
    }

    @Override
    public V1Service createByName(String namespace, ServiceDto serviceDto) throws ApiException {
        //获取service 名
        String serviceName = serviceDto.getName();
        //获取集群内服务访问端口
        int nodePort = serviceDto.getNodePort();
        //获取集群外服务访问端口
        int targetPort = serviceDto.getTargetPort();
        //获取 Kubernetes API
        CoreV1Api api = new CoreV1Api();
        //创建Service实例，其实就是模拟yaml配置文件生成
        V1Service v1Service = new V1Service()
                .apiVersion("v1")
                .metadata(new V1ObjectMeta()
                        //service 名
                        .name(serviceName)
                        //添加 service 标签
                        .putLabelsItem("app", serviceDto.getName()))
                .spec(new V1ServiceSpec()
                        //pod 选择器
                        .selector(serviceDto.getLabels()));
        //如果配置了集群对外暴露端口，则添加属性
        if (serviceDto.getTargetPort() != 0) {
            v1Service.getSpec()
                    .addPortsItem(new V1ServicePort()
                            //port 名
                            .name(serviceName + "-" + "port")
                            //集群内服务访问端口
                            .port(targetPort)
                            //集群外服务访问端口
                            .nodePort(nodePort)
                            //请求转发端口
                            .targetPort(new IntOrString(targetPort)))
                    //设置服务类型为NodePort，即可暴露在集群外
                    .type("NodePort");
        }
        return api.createNamespacedService(namespace, v1Service, null, null, null, null);
    }
}

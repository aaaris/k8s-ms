# 项目简介

k8s-ms 是一个基于 [client-java](https://github.com/kubernetes-client/java) 库，使用 springboot 搭建的 web 服务器。

## 功能介绍

- 使用 `~/.kube/config` 配置文件发现集群，使得在集群外部署的服务器也可以访问集群。

- 接收 http 请求，对 pod、deployment、service 三种资源实现 CR 操作。

- 接收 body 为 yaml 格式的 http 请求，实现容器的创建（部署、公布应用程序）

> `~/.kube/config`存储的是 k8s 自动生成配置文件。
>
> 对于使用 k3s 的用户，该配置文件通常位于 `/etc/rancher/k3s/k3s.yaml`

## 使用技术

| 技术栈         | 说明                    |
|-------------|-----------------------|
| SpringBoot  | 实现Web服务器              |
| client-java | 通过Kubernetes API 操作集群 |
| Swagger3    | 自动化生成API文档，方便查阅调试     |
| lombok      | 利用注解，快速生成get/set方法等   |

# Api 接口文档

本项目的 api docs 使用 [Swagger3](https://github.com/swagger-api/swagger-core) 自动生成，方便调试运行。

运行项目后，在浏览器打开 [http://localhost:8080/swagger-api/index.html](http://localhost:8080/swagger-api/index.html) 查看。

# 后续计划

- 在响应中增加更多API报错信息。

# 拓展阅读

[client-java](https://github.com/kubernetes-client/java)

[Code-Examples](https://github.com/kubernetes-client/java/wiki/3.-Code-Examples)

[Kubernetes](https://kubernetes.io/)

[Kuboard Learning](https://kuboard.cn/learning/)





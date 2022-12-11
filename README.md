# 项目简介

k8s-ms 是一个基于 [client-java](https://github.com/kubernetes-client/java) 库，使用 [SpringBoot](https://github.com/spring-projects/spring-boot) 搭建的容器运行管理系统。

## 功能介绍

- 使用 `~/.kube/config` 配置文件发现集群，使得在集群外部署的服务器也可以访问集群。

- 接收 http 请求，对 pod、deployment、service 三种资源实现 CR 操作。

- 可以简单传递镜像名和节点名，或传入yaml，实现在指定虚拟机容器的创建、应用程序部署公布

> `~/.kube/config`存储的是 k8s 自动生成配置文件。
>
> 对于使用 k3s 的用户，该配置文件通常位于 `/etc/rancher/k3s/k3s.yaml`

## 使用技术

| 技术栈            | 说明                    |
|----------------|-----------------------|
| SpringBoot Web | 快速搭建Web服务器            |
| client-java    | 通过Kubernetes API 操作集群 |
| Swagger3       | 自动化生成在线API文档，方便查阅调试   |
| lombok         | 利用注解，快速生成get/set方法等   |

# Api 接口文档

本项目的 api docs 使用 [Swagger3](https://github.com/swagger-api/swagger-core) 自动生成，方便调试运行。

运行项目后，在浏览器打开 [http://localhost:8080/swagger-api/index.html](http://localhost:8080/swagger-api/index.html) 查看。

# 搭建环境

> 使用 [kubernetes](https://kubernetes.io/) 搭建集群环境

1. 进入集群的 `master` 节点（控制面节点）虚拟机
2. 查看其文件目录 `~/.kube/` 下的 `config` 文件（确保kubelet服务正确运行）
3. 将该 `config` 文件拷贝到本地 `~/.kube/` 下（Windows系统为 `<当前用户目录>/.kube/`）
4. 在 [releases](https://github.com/aaaris/k8s-ms/releases) 下载最近版本的 jar 包
5. 确保本地安装了 `Java8` 及以上环境，且配置了系统环境变量
6. 在下载目录，进入控制台，使用 `java -jar <对应版本下载jar包>` 运行Web服务器

> 使用 [k3s](https://k3s.io) 搭建集群环境

1. 进入集群的 `master` 节点（控制面节点）虚拟机
2. 查看其文件目录 `~/.kube/` 下的 `config` 文件（确保 `kubelet` 服务正确运行）
3. 将该 `config` 文件拷贝到本地 `~/.kube/` 下（Windows系统为 `<当前用户目录>/.kube/`）
4. 在 [releases](https://github.com/aaaris/k8s-ms/releases) 下载最近版本的 jar 包
5. 确保本地安装了 `Java8` 及以上环境，且配置了系统环境变量
6. 在下载目录，进入控制台，使用 `java -jar <对应版本下载jar包>` 运行Web服务器

> 使用 [minikube](https://minikube.sigs.k8s.io/docs/) 搭建集群环境

1. 进入集群的 `master` 节点（控制面节点）虚拟机
2. 查看其文件目录 `~/.minikube` 下的 `config` 文件（确保 `minikube` 服务正确运行）
3. 将该 `config` 文件拷贝到本地 `~/.kube/` 下（Windows系统为 `<当前用户目录>/.kube/`）
4. 在 [releases](https://github.com/aaaris/k8s-ms/releases) 下载最近版本的 jar 包
5. 确保本地安装了 `Java8` 及以上环境，且配置了系统环境变量
6. 在下载目录，进入控制台，使用 `java -jar <对应版本下载jar包>` 运行Web服务器 

>可选：源码搭建

1. （源码搭建）使用 `git clone https://github.com/aaaris/k8s-ms.git` 克隆仓库
2. （源码搭建）确保本地安装了 `maven`，且配置了系统环境变量
3. （源码运行）进入项目目录，在控制台输入 `mvn spring-boot:run`启动项目
4. （源码打包部署）进入项目目录，在控制台输入 `mvn mvn clean package`打包项目
5. （源码打包部署）打包后，将生成的 `target` 目录出现的 `k3s-x.x.x-SNAPSHOT.jar` 文件迁移目标服务器
6. （源码打包部署）确保目标服务器安装了 `Java8` 及以上环境
7. （源码打包部署）使用 `java -jar <在target目录jar包>` 运行Web服务器

# 常见错误

> 运行项目，Kubernetes API无法找到指定控制面主机

1. 检查 `config` 文件中 `server ip` 是否为控制面主机的 ip
2. 利用 `ping` 命令检查本地宿主机和虚拟机能否ping通，并检查防火墙设置。

# 后续计划

- 在响应中增加更多API报错信息。

# 拓展阅读

[client-java](https://github.com/kubernetes-client/java)

[Code-Examples](https://github.com/kubernetes-client/java/wiki/3.-Code-Examples)

[Kubernetes](https://kubernetes.io/)

[Kuboard Learning](https://kuboard.cn/learning/)





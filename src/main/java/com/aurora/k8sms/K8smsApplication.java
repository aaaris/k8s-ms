package com.aurora.k8sms;

import com.aurora.k8sms.config.kubeConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@Slf4j
@SpringBootApplication
public class K8smsApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(K8smsApplication.class, args);
        kubeConfig.init();
        log.info("项目启动！！！");
    }

}

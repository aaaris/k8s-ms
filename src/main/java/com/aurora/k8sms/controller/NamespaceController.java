package com.aurora.k8sms.controller;

import com.aurora.k8sms.common.utils.R;
import com.aurora.k8sms.service.NamespaceService;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Namespace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/namespace")
public class NamespaceController {
    @Autowired
    private NamespaceService namespaceSerivce;

    @GetMapping("")
    public R getAll() throws  ApiException {
        // TODO: 测试接口
        List<V1Namespace> namespaceList = namespaceSerivce.list();
        return R.success(namespaceList);
    }

    @PostMapping("/{name}")
    public R add(@PathVariable String name) throws  ApiException {
        // TODO: 测试接口
        V1Namespace namespace = namespaceSerivce.create(name);
        return R.success(namespace);
    }

}

package com.aurora.k8sms.controller;

import com.aurora.k8sms.common.utils.R;
import com.aurora.k8sms.service.NamespaceService;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "查询 namespace", notes = "查询所有命名空间")
    @GetMapping("")
    public R getAll() throws  ApiException {
        List<V1Namespace> namespaceList = namespaceSerivce.list();
        return R.success(namespaceList);
    }

    @ApiOperation(value="新增 namespace",notes = "根据name新建namespace")
    @PostMapping("/{name}")
    public R add(@PathVariable String name) throws  ApiException {
         V1Namespace namespace = namespaceSerivce.create(name);
        return R.success(namespace);
    }

}

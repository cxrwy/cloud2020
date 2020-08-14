package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
    //List<ServiceInstance> instances=discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

}

package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced  //负载均衡,若注释掉,又没有使用自定义的负载均衡,会报错,因为是集群,不知道该调用哪个服务器的服务
    public RestTemplate getRestTemplate(){
        return  new RestTemplate();
    }
}

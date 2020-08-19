package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;
    @PostMapping("/payment/create")
    public CommonResult creat(@RequestBody Payment payment){
        int result=paymentService.create(payment);
        log.info("**插入结果**:"+result);
        if(result>0){
            return new CommonResult(200,"插入数据库成功,端口号:"+serverPort,result);

        }else{
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment result=paymentService.getPaymentById(id);
        log.info("**获取**:"+result+"嘿嘿");
        if(result!=null){
            return new CommonResult(200,"查询成功,serverport:"+serverPort,result);

        }else{
            return new CommonResult(444,"没有对应记录,查询ID:"+id+"失败",null);
        }
    }
    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for(String element:services){
            log.info("**element:"+element);
        }
        List<ServiceInstance> instances=discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance: instances){
            log.info(instance.getInstanceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }
    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping("/payment/feign/timeout")
    public  String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        return "im paymentzipkin server fallback";
    }

}

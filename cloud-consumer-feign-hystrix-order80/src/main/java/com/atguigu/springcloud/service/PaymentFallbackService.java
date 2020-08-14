package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----paymentFallbackService fall back-paymentInfo_OK";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "----paymentFallbackService fall back-paymentInfo_timeout";
    }
}

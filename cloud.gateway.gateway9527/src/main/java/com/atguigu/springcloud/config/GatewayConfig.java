package com.atguigu.springcloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes= routeLocatorBuilder.routes();
        routes.route("path_route_auguigu",
                r->r.path("/guonei")
                        .uri("https://news.baidu.com/guonei")).build();
        return routes.build();

    }
    @Bean
    public RouteLocator customerRouteLocator2(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes= routeLocatorBuilder.routes();
        routes.route("path_route_auguigu",
                r->r.path("/game")
                        .uri("https://news.baidu.com/game")).build();
        return routes.build();

    }

}

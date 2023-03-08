package de.leuphana.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
    
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("customer-service", r -> r.path("/customers/**")
                .uri("lb://customer-service"))
            .route("order-service", r -> r.path("/orders/**")
                .uri("lb://order-service"))
            .route("article-service", r -> r.path("/articles/**")
                .uri("lb://article-service"))
            .route("shop-service", r -> r.path("/shops/**")
                .uri("lb://shop-service"))
            .build();
    }
}

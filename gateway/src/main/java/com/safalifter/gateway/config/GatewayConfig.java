package com.safalifter.gateway.config;


import com.safalifter.gateway.filter.JwtAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class GatewayConfig {

    private final JwtAuthenticationFilter filter;

    public GatewayConfig(JwtAuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        log.info("RouteLocatorBuilder:{}", builder);
        return builder.routes()
                .route("user-service", r ->
                    r.path("/api/v1/user/**",
                            "/api/v1/role/**",
                            "/api/v1/user-group/**",
                            "/api/v1/account/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://user-service"))

                .route("auth-service", r ->
                    r.path("/api/v1/author/**")
                        .uri("lb://auth-service"))

                .route("org-service", r ->
                    r.path("/api/v1/organization/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://org-service"))

                .route("config-system-service",
                    r -> r.path("/api/v1/rule-base/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://config-system-service"))

                .route("business-service", r ->
                    r.path("/api/v1/business/**" ,
                        "/api/v1/approval/**",
                            "/api/v1/business-detail/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://business-service"))
            .build();
    }
}
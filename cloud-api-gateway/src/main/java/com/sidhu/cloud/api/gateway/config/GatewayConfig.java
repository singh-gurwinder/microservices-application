package com.sidhu.cloud.api.gateway.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class GatewayConfig {
    @Autowired
    private AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("AUTH-SERVICE", r -> r.path("/auth/**")
                        .filters(f -> {
                            f.circuitBreaker(c -> c.setName("authFallback")
                                    .setFallbackUri("/authFallback"));
                            return f.filter(filter);
                        })
                        .uri("lb://AUTH-SERVICE"))
                .route("USER-SERVICE", r -> r.path("/users/**")
                        .filters(f -> {
                            f.filter(filter);
                            return f.circuitBreaker(c -> c.setName("usersFallback")
                                    .setFallbackUri("/usersFallback"));
                        })
                        .uri("lb://USER-SERVICE"))
                .route("ACCOUNT-SERVICE", r -> r.path("/accounts/**")
                        .filters(f -> {
                            f.filter(filter);
                            return f.circuitBreaker(c -> c.setName("accountsFallback")
                                    .setFallbackUri("/accountsFallback"));
                        })
                        .uri("lb://ACCOUNT-SERVICE"))
                .route("TRANSACTION-SERVICE", r -> r.path("/transactions/**")
                        .filters(f -> {
                            f.filter(filter);
                            return f.circuitBreaker(c -> c.setName("transactionsFallback")
                                    .setFallbackUri("/transactionsFallback"));
                        })
                        .uri("lb://TRANSACTION-SERVICE"))
                .build();
    }

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
            .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
            .timeLimiterConfig(TimeLimiterConfig.custom()
                    .timeoutDuration(Duration.ofSeconds(2)).build()).build());
    }
}

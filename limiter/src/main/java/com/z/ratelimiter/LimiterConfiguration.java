package com.z.ratelimiter;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 将限流组件托管到spring
 */
@Configuration
@ComponentScan(basePackages = "com.z.ratelimiter")
public class LimiterConfiguration {
}

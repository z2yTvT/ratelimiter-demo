package com.z.ratelimiter.guava.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Component
@Autowired(required = false)
public @interface ExecuteRateLimit {
    /**
     * 返回的RateLimiter的速率，意味着每秒有多少个许可变成有效。
     */
    int permitsPerSecond() default 5;

    /**
     * 在这段时间内RateLimiter会增加它的速率，在抵达它的稳定速率或者最大速率之前
     */
    int warmupPeriod() default 5;

    /**
     * warmupPeriod 的时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

}

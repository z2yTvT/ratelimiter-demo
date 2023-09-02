package com.z.ratelimiter.guava.aop;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.RateLimiter;
import com.z.ratelimiter.guava.GuavaLimiterFactory;
import com.z.ratelimiter.guava.GuavaLimiterParam;
import com.z.ratelimiter.guava.annotation.ExecuteRateLimit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Aspect
@Component
public class GuavaExecuteLimitAspect {

    @Autowired
    private GuavaLimiterFactory guavaLimiterFactory;

    private Map<String, RateLimiter> limiterMap = new ConcurrentHashMap<>();

    private Joiner joiner = Joiner.on("-").skipNulls();

    @Pointcut("@annotation(com.z.ratelimiter.guava.annotation.ExecuteRateLimit)")
    public void match() {
    }

    @Around("match()")
    public Object doMethod(ProceedingJoinPoint pjp){
        MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
        Method method = methodSignature.getMethod();
        ExecuteRateLimit annotation = method.getAnnotation(ExecuteRateLimit.class);
        GuavaLimiterParam param = GuavaLimiterParam.builder()
                .permitsPerSecond(annotation.permitsPerSecond())
                .warmupPeriod(annotation.warmupPeriod())
                .unit(annotation.timeUnit())
                .build();
        String k = joiner.join(param.getPermitsPerSecond(), param.getWarmupPeriod(), param.getUnit().toString());
        RateLimiter limiter = limiterMap.computeIfAbsent(k, p -> guavaLimiterFactory.createLimiter(param));

        try {
            double v = limiter.acquire();
            log.info("执行限流:令牌桶速率：{}，sleepTime：{}",annotation.permitsPerSecond(),v);
            return pjp.proceed();
        }catch (Throwable e){
            log.error("限流失败",e);
            return null;
        }
    }
}

package com.z.ratelimiter.guava;

import com.google.common.util.concurrent.RateLimiter;
import com.z.ratelimiter.limiter.LimiterFactory;
import org.springframework.stereotype.Component;

@Component
public class GuavaLimiterFactory implements LimiterFactory<GuavaLimiterParam, RateLimiter> {
    @Override
    public RateLimiter createLimiter(GuavaLimiterParam guavaLimiterParam) {
        return RateLimiter.create(guavaLimiterParam.getPermitsPerSecond(),
                                    guavaLimiterParam.getWarmupPeriod(),
                                    guavaLimiterParam.getUnit());
    }
}

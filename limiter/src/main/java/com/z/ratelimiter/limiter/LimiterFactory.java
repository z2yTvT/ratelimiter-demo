package com.z.ratelimiter.limiter;

@FunctionalInterface
public interface LimiterFactory<P,R> {
    R createLimiter(P p);
}

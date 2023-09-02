package com.z.ratelimiter.guava;


import lombok.Builder;
import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
@Builder
public class GuavaLimiterParam {

    private int permitsPerSecond;

    private long warmupPeriod;

    private TimeUnit unit;
}

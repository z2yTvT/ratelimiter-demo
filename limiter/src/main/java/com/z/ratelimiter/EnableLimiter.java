package com.z.ratelimiter;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 对外暴露注解，导入LimiterConfiguration
 */
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Import(LimiterConfiguration.class)
public @interface EnableLimiter {
}

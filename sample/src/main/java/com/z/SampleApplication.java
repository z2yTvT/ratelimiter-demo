package com.z;

import com.z.ratelimiter.EnableLimiter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableLimiter
public class SampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class);
    }
}

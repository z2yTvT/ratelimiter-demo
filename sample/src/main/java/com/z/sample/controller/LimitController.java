package com.z.sample.controller;

import com.z.ratelimiter.guava.annotation.ExecuteRateLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LimitController {
    @GetMapping("/limit")
    @ExecuteRateLimit(permitsPerSecond = 1)
    public String limited(){
        return "limit";
    }
}

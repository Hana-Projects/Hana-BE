package com.hanabridge.api.global.aws.target;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/health")
    public Long healthcheck(){
        return 200L;
    }
}

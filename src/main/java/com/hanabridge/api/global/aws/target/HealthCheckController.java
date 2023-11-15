package com.hanabridge.api.global.aws.target;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Health Check", description = "AWS ELB health check")
public class HealthCheckController {

    @GetMapping("/health")
    public Long healthcheck() {
        return 200L;
    }
}

package com.hanabridge.api.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
            .components(new Components())
            .info(new Info()
                .title("API 명세서")
                .description("하나 디지털 파워 온 프로젝트 팀명 '하나브릿지' API 명세서")
                .version("1.0.0"));
    }

}

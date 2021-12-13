package com.mmlrdev.springMVC.service.configs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "mock.api")
public class UserApiConfig {
    private String schema;
    private String hostname;

    @Bean("configRestTemplateUserApiConfig")
    public RestTemplate configRestTemplateUserApiConfig() {
        return new RestTemplate();
    }
}




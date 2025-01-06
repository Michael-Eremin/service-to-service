package com.example.stockService.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private Environment env;

    public WebClientConfig(@Autowired Environment env) {
        this.env = env;
    }

    @Bean
    public WebClient webClient() {
        return WebClient.create(env.getProperty("spring.webclient.url.report"));
    }
}

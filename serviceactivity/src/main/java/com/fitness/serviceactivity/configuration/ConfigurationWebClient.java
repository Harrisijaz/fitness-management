package com.fitness.serviceactivity.configuration;

import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ConfigurationWebClient {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder(){
       return WebClient.builder();

     }

     @Bean
     public WebClient userServiceWebClient(WebClient.Builder webClientBuilder){
        return webClientBuilder.baseUrl("http://SERVICE-USER")
                .build();
     }

}

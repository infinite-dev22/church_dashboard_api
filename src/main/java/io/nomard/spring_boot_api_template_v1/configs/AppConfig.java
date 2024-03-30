package io.nomard.spring_boot_api_template_v1.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.nomard.spring_boot_api_template_v1.entities.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public User user() {
        return new User();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}

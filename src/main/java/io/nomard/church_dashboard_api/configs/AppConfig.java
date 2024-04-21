package io.nomard.church_dashboard_api.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.nomard.church_dashboard_api.entities.Member;
import io.nomard.church_dashboard_api.entities.User;
import io.nomard.church_dashboard_api.services.auth.SpotyTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("jwt.secret")
    private String secret;

    @Bean
    public Member member() {
        return new Member();
    }

    @Bean
    public User user() {
        return new User();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public SpotyTokenService spotyTokenService() {
        return new SpotyTokenService(secret);
    }
}

package org.example.authentication;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource("classpath:application.yaml")
public class AuthConfig {
    @Value("${backend.app.jwtSecret}")
    private String jwtSecret;

    @Value("${backend.app.jwtExpirationMs}")
    private String jwtExpirationMs;
}

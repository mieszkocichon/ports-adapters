package org.example.authentication;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource("classpath:application.yaml")
public class AuthConfig {
    @Value("${shop.app.jwtSecret}")
    private String jwtSecret;

    @Value("${shop.app.jwtExpirationMs}")
    private String jwtExpirationMs;
}

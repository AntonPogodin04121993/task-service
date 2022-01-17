package com.task.taskservice.conf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.security")
@Getter
@Setter
public class CustomSecurityProperties {

    private String secret;

    private Long expirationTokenTime;

    private Long expirationRefreshTokenTime;
}

package com.health.mental.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@ConfigurationProperties(prefix = "mental-health")
public class MentalHealthConfig {

    private LoginData login;

    public LoginData getLogin() {
        return login;
    }

    public void setLogin(final LoginData login) {
        this.login = login;
    }

    public record LoginData(String username, String password) {}
}

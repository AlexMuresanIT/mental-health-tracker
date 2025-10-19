package com.health.mental.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@ConfigurationProperties(prefix = "mental-health")
public class MentalHealthConfig {

  private LoginData login;
  private String locationBaseUrl;

  public LoginData getLogin() {
    return login;
  }

  public void setLogin(final LoginData login) {
    this.login = login;
  }

  public String getLocationBaseUrl() {
    return locationBaseUrl;
  }

  public void setLocationBaseUrl(final String locationBaseUrl) {
    this.locationBaseUrl = locationBaseUrl;
  }

  public record LoginData(String username, String password) {}
}

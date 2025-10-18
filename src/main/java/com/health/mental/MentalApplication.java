package com.health.mental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan("com.health.mental")
public class MentalApplication {

  public static void main(final String[] args) {
    SpringApplication.run(MentalApplication.class, args);
  }
}

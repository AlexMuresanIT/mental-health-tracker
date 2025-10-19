package com.health.mental.config;

import com.health.mental.repository.DateTimeReadConverter;
import com.health.mental.repository.DateTimeWriteConverter;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

@Configuration
public class MongoConfiguration {

  @Bean
  MongoCustomConversions customConversions() {
    return new MongoCustomConversions(
        List.of(new DateTimeReadConverter(), new DateTimeWriteConverter()));
  }
}

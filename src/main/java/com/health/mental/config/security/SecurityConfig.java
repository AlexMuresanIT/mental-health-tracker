package com.health.mental.config.security;

import com.health.mental.config.MentalHealthConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  private final String username;
  private final String password;

  public SecurityConfig(final MentalHealthConfig mentalHealthConfig) {
    this.username = mentalHealthConfig.getLogin().username();
    this.password = mentalHealthConfig.getLogin().password();
  }

  @Autowired
  public void configureGlobal(final AuthenticationManagerBuilder auth) {
    auth.eraseCredentials(false);
  }

  @Bean
  public UserDetailsService userDetailsService() {
    final var admin =
        User.builder().username(username).password(password).roles(Role.ADMIN.name()).build();

    return new InMemoryUserDetailsManager(admin);
  }

  @Bean
  public SecurityFilterChain configure(final HttpSecurity http) throws Exception {
    return http.csrf(AbstractHttpConfigurer::disable)
        .httpBasic(Customizer.withDefaults())
        .securityMatcher("/**")
        .authorizeHttpRequests(request -> request.anyRequest().authenticated())
        .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}

package com.health.mental.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public record User(
    @Id String id, String name, String email, Gender gender, Integer age, String password) {}

package com.health.mental.domain.dto;

import com.health.mental.domain.Gender;

public record UserDTO(String name, String email, Gender gender, Integer age, String password) {}

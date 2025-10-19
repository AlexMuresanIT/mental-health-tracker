package com.health.mental.domain.dto;

import com.health.mental.domain.Gender;
import java.util.List;

public record UserDTO(
    String name,
    String email,
    Gender gender,
    Integer age,
    String password,
    List<MoodLogDTO> userMoodLogs) {}

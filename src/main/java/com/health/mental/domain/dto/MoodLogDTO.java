package com.health.mental.domain.dto;

import com.health.mental.domain.MoodTags;
import com.health.mental.domain.MoodType;
import java.util.Set;

public record MoodLogDTO(
    MoodType mood, String notes, Integer intensity, Set<MoodTags> tags, String city) {}

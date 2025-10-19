package com.health.mental.domain;

public record Location(
    String country, String city, Double latitude, Double longitude, String timezone) {}

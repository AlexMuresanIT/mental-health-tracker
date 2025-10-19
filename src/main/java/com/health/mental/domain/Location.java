package com.health.mental.domain;

public record Location(
    String country, String city, Double latitude, Double longitude, String timezone) {

  public static Location getDefaultLocation() {
    return new Location("Unknown", "Unknown", 0.0, 0.0, "UTC");
  }
}

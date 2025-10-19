package com.health.mental.service;

import com.health.mental.config.MentalHealthConfig;
import com.health.mental.domain.Location;
import com.health.mental.domain.dto.LocationDTO;
import com.health.mental.mapper.LocationMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class LocationService {

  private final WebClient client;
  private final LocationMapper locationMapper;

  public LocationService(
      final WebClient.Builder builder,
      final MentalHealthConfig config,
      final LocationMapper locationMapper) {
    this.locationMapper = locationMapper;
    this.client =
        builder
            .baseUrl(config.getLocationBaseUrl())
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
  }

  public Location getLocationForIpAddress(final String ipAddress) {
    final var locationDTO =
        client
            .get()
            .uri(uriBuilder -> uriBuilder.path("/" + ipAddress).build())
            .retrieve()
            .bodyToMono(LocationDTO.class)
            .block();
    return getLocation(locationDTO);
  }

  private Location getLocation(final LocationDTO locationDTO) {
    if (locationDTO == null) {
      return null;
    }
    return locationMapper.from(locationDTO);
  }
}

package com.health.mental.service;

import com.health.mental.domain.MoodLog;
import com.health.mental.repository.MoodLogRepository;
import java.time.OffsetDateTime;
import org.springframework.stereotype.Service;

@Service
public class MoodLogService {

  private final MoodLogRepository moodLogRepository;
  private final UserService userService;
  private final LocationService locationService;

  public MoodLogService(
      final MoodLogRepository moodLogRepository,
      final UserService userService,
      final LocationService locationService) {
    this.moodLogRepository = moodLogRepository;
    this.userService = userService;
    this.locationService = locationService;
  }

  public void addMoodLogForUser(
      final String userId, final String ipAddress, final MoodLog moodLog) {
    final var maybeUser = userService.findById(userId);
    maybeUser.ifPresent(
        user -> {
          final var location = locationService.getLocationForIpAddress(ipAddress);
          final var now = OffsetDateTime.now();
          final var enhancedMoodLog = MoodLog.enhanceMood(userId, moodLog, location, now);
          moodLogRepository.save(enhancedMoodLog);
          userService.addMoodLogForUser(user, enhancedMoodLog);
        });
  }
}

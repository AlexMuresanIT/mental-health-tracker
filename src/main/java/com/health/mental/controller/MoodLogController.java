package com.health.mental.controller;

import com.health.mental.config.security.SecurityUtils;
import com.health.mental.domain.dto.MoodLogDTO;
import com.health.mental.mapper.MoodLogMapper;
import com.health.mental.service.MoodLogService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mood")
public class MoodLogController {

  private final MoodLogService moodLogService;
  private final MoodLogMapper moodLogMapper;

  public MoodLogController(final MoodLogService moodLogService, final MoodLogMapper moodLogMapper) {
    this.moodLogService = moodLogService;
    this.moodLogMapper = moodLogMapper;
  }

  @PostMapping("/add/{userId}")
  public void addMoodLog(
      final HttpServletRequest request,
      @PathVariable final String userId,
      @RequestBody final MoodLogDTO moodLogDTO) {
    final var userIpAddress = SecurityUtils.getUserIpAddress(request);
    final var domainMoodLog = moodLogMapper.to(moodLogDTO);
    moodLogService.addMoodLogForUser(userId, userIpAddress, domainMoodLog);
  }
}

package com.health.mental.mapper;

import com.health.mental.domain.MoodLog;
import com.health.mental.domain.dto.MoodLogDTO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface MoodLogMapper {

  @Mapping(target = "city", source = "moodLog.location.city")
  MoodLogDTO from(MoodLog moodLog);

  MoodLog to(MoodLogDTO moodLogDTO);

  List<MoodLogDTO> from(List<MoodLog> moodLogs);

  List<MoodLog> to(List<MoodLogDTO> moodLogDTOs);
}

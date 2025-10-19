package com.health.mental.mapper;

import com.health.mental.domain.Location;
import com.health.mental.domain.dto.LocationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface LocationMapper {

  @Mapping(target = "longitude", source = "locationDTO.lon")
  @Mapping(target = "latitude", source = "locationDTO.lat")
  Location from(LocationDTO locationDTO);
}

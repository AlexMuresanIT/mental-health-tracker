package com.health.mental.repository;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class DateTimeReadConverter implements Converter<Date, OffsetDateTime> {
  @Override
  public OffsetDateTime convert(final Date date) {
    return date.toInstant().atOffset(ZoneOffset.UTC);
  }
}

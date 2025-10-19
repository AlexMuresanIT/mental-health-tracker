package com.health.mental.repository;

import java.time.OffsetDateTime;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class DateTimeWriteConverter implements Converter<OffsetDateTime, Date> {
  @Override
  public Date convert(final OffsetDateTime dateTime) {
    return Date.from(dateTime.toInstant());
  }
}

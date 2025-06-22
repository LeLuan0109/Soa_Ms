package com.project.app.core.validation;

import com.project.app.core.common.request.TimeRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeRangeValidator implements ConstraintValidator<ValidTimeRange, TimeRange> {
  private static final int MAX_YEARS_DIFFERENCE = 1;
  private static final int MAX_DAYS_DIFFERENCE = 365;
  @Override
  public boolean isValid(TimeRange timeRange, ConstraintValidatorContext context) {
    if (timeRange.getStartDate() == null || timeRange.getEndDate() == null) {
      return false; // Invalid if any value is null
    }

    Instant start = Instant.ofEpochSecond(timeRange.getStartDate());
    Instant end = Instant.ofEpochSecond(timeRange.getEndDate());
    long daysDifference = ChronoUnit.DAYS.between(start, end);
    long yearsDifference = daysDifference / MAX_DAYS_DIFFERENCE; // Approximate

    return yearsDifference < MAX_YEARS_DIFFERENCE;
  }
}

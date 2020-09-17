package com.thoughtworks.capability.gtb;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * 计算任意日期与下一个劳动节相差多少天
 *
 * @author itutry
 * @create 2020-05-15_16:33
 */
public class Practice1 {
  private static int Year = LocalDate.now().getYear();
  private static final LocalDate CURRENT_LABOR_DAY = LocalDate.of(Year, 5,1);
  public static long getDaysBetweenNextLaborDay(LocalDate date) {
    if (date.isAfter(CURRENT_LABOR_DAY)) {
      LocalDate nextLaborDay = CURRENT_LABOR_DAY.plusYears(1);
      return ChronoUnit.DAYS.between(date, nextLaborDay);
    }

    return ChronoUnit.DAYS.between(date, CURRENT_LABOR_DAY);
  }
}

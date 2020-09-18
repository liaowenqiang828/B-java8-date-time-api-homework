package com.thoughtworks.capability.gtb;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * 对任意日期获取下一个工作日, 不考虑节假日
 *
 * @author itutry
 * @create 2020-05-15_17:20
 */
public class Practice2 {
  private static final int NEXT_WORKDAY_NEAR_FRIDAY = 3;
  private static final int NEXT_WORKDAY_NEAR_SATURDAY = 2;
  private static final int NEXT_WORKDAY = 1;

  public static LocalDate getNextWorkDate(LocalDate date) {
    DayOfWeek dayOfWeek = date.getDayOfWeek();
    if (dayOfWeek == DayOfWeek.FRIDAY) {
      return date.plusDays(NEXT_WORKDAY_NEAR_FRIDAY);
    }
    if (dayOfWeek == DayOfWeek.SATURDAY) {
      return date.plusDays(NEXT_WORKDAY_NEAR_SATURDAY);
    }
    return date.plusDays(NEXT_WORKDAY);
  }
}

package com.thoughtworks.capability.gtb;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 脑洞会议系统v3.0
 * 1.当前会议时间"2020-04-01 14:30:00"表示伦敦的本地时间，而输出的新会议时间是芝加哥的本地时间
 *   场景：
 *   a:上个会议是伦敦的同事定的，他在界面上输入的时间是"2020-04-01 14:30:00"，所以我们要解析的字符串是伦敦的本地时间
 *   b:而我们在当前时区(北京时区)使用系统
 *   c:我们设置好新会议时间后，要发给芝加哥的同事查看，所以格式化后的新会议时间要求是芝加哥的本地时间
 * 2.用Period来实现下个会议时间的计算
 *
 * @author itutry
 * @create 2020-05-19_18:43
 */
public class MeetingSystemV3 {

  public static void main(String[] args) {
    String timeStr = "2020-04-01 14:30:00";
    final ZoneId LONDON_ZONE_ID = ZoneId.of("Europe/London");
    final ZoneId BEIJING_ZONE_ID = ZoneId.of("Asia/Shanghai");
    final ZoneId CHICAGO_ZONE_ID = ZoneId.of("America/Chicago");
    final int NEXT_DAY = 1;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime meetingTime = LocalDateTime.parse(timeStr, formatter);
    LocalDateTime beiJingMeetingTime = meetingTime.atZone(LONDON_ZONE_ID)
            .withZoneSameInstant(BEIJING_ZONE_ID)
            .toLocalDateTime();
    LocalDateTime now = LocalDateTime.now();
    if (now.isAfter(beiJingMeetingTime)) {
      Period period = Period.between(now.toLocalDate(), beiJingMeetingTime.toLocalDate());
      beiJingMeetingTime = beiJingMeetingTime
              .plusMonths(period.getMonths())
              .plusDays(period.getDays())
              .plusDays(NEXT_DAY);
      LocalDateTime chicagoMeetingTime = beiJingMeetingTime
              .atZone(BEIJING_ZONE_ID)
              .withZoneSameInstant(CHICAGO_ZONE_ID)
              .toLocalDateTime();
      String showTimeStr = formatter.format(chicagoMeetingTime);
      System.out.println(showTimeStr);
    } else {
      System.out.println("会议还没开始呢");
    }
  }
}

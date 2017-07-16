package com.il.sod;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by cesaregb on 7/16/17.
 */
public class TestUtils {

  public static Timestamp toTimestamp(LocalDate localDate) {
    Date date = Date.from(localDate.atStartOfDay()
            .atZone(ZoneId.systemDefault()).toInstant());
    return new Timestamp(date.getTime());
  }
}

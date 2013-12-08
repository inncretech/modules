package com.inncretech.core.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class DateTimeUtils {

  public static DateTime currentTimeWithoutFractionalSeconds() {
    return new DateTime(DateTimeZone.UTC).secondOfMinute().roundFloorCopy();
  }
}

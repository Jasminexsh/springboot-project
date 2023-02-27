package com.jasmine.springboot.utils;

import com.jasmine.springboot.util.DateUtil;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;

/**
 * @author xieshanghan
 * @version DateUtilTests.java, v 0.1 2023年02月03日 16:40 xieshanghan
 */
public class DateUtilTests {

    @Test
    public void testDefaultZoneId() {
        System.out.println(DateUtil.getJvmDefaultZoneId());
        System.out.println(DateUtil.getJvmDefaultZoneIdString());
    }

    @Test
    public void testDate2String() {
        Date date = new Date();
        String pattern = DateUtil.DAY_DATE_FORMAT_PATTERN;
        ZoneId zoneId = ZoneId.of(ZoneId.SHORT_IDS.get("AET"));

        String dateString1 = DateUtil.date2String(date);
        String dateString2 = DateUtil.date2String(date, pattern);
        String dateString3 = DateUtil.date2String(date, DateUtil.DEFAULT_DATE_FORMAT_PATTERN);
        String dateString4 = DateUtil.date2String(date, DateUtil.DEFAULT_DATE_FORMAT_PATTERN, TimeZone.getTimeZone(zoneId));

        System.out.println(dateString1);
        System.out.println(dateString2);
        System.out.println(dateString3);
        System.out.println(dateString4);
    }

    @Test
    public void testAvailableZoneIds() {
        Set<String> zoneIdSet =  ZoneId.getAvailableZoneIds();
        for (String str : zoneIdSet) {
            System.out.println(str);
        }
    }

    @Test
    public void testDateStringConvert() {
        String sourString = "2023-02-03 17:53:19";
        String destStringDay = DateUtil.dateStringConvert(sourString, DateUtil.DEFAULT_DATE_FORMAT_PATTERN, DateUtil.DAY_DATE_FORMAT_PATTERN);
        String destStringHour = DateUtil.dateStringConvert(sourString, DateUtil.DEFAULT_DATE_FORMAT_PATTERN, DateUtil.HOUR_DATE_FORMAT_PATTERN);
        System.out.println("2023-02-03".equals(destStringDay));
        System.out.println("17:53:19".equals(destStringHour));
    }

    @Test
    public void testTimeCompare() {
        String fromTimeStr  = "2023-02-05 11:25:00";
        String toTimeStr    = "2023-02-04 11:25:00";
        Date   fromTimeDate = DateUtil.string2Date(fromTimeStr);
        Date   toTimeDate   = DateUtil.string2Date(toTimeStr);
        System.out.println(DateUtil.timeCompare(fromTimeDate, toTimeDate));
    }

}
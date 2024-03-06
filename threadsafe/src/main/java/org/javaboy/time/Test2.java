package org.javaboy.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author:majin.wj
 * https://blog.csdn.net/qq_21845263/article/details/106203865
 * Date: Date本质是记录了0时区的时间，不同时区的人在同一时刻new Date()时，其对象内存放的毫秒数是一样的（都是0时区）
 * toString方法的时候，会准换成当前操作系统所设置的时区时间。
 *
 * TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai")) 设置当前系统时区
 *
 * Instant:本质是记录了0时区的时间
 * new Date()和Instant.now本质上都是记录了0时区的时间，即使当前时区不同，其存储的都是距离1970-01-01 00:00:00所经过的时间。
 *
 */
public class Test2 {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT")); //GMT时间(格林尼治标准时间)一般指世界时, 即0时区的区时,比北京时间(东8区)晚8小时
        Date d1 = new Date();
        Instant i1 = Instant.now();
        ZonedDateTime z1 = ZonedDateTime.now();
        LocalDateTime l1 = LocalDateTime.now();
        System.out.println(d1);


        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));// == GMT+8时区
        Date d2 = new Date();
        Instant i2 = Instant.now();
        ZonedDateTime z2 = ZonedDateTime.now();
        LocalDateTime l2 = LocalDateTime.now();

        System.out.println(d2);
        TimeZone.setDefault(TimeZone.getTimeZone("Australia/Darwin"));
        Date d3 = new Date();
        Instant i3 = Instant.now();
        ZonedDateTime z3 = ZonedDateTime.now();
        LocalDateTime l3 = LocalDateTime.now();

        System.out.println(d3);

        ZonedDateTime gmt = Instant.now().atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(gmt.toLocalDateTime());


    }
}

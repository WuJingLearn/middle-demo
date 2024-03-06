package org.javaboy.time;

import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author:majin.wj
 */
public class Test1 {

    public static void main(String[] args) {
        Instant now = Instant.now();
        System.out.println(now);

        System.out.println(now.getEpochSecond());
        System.out.println(System.currentTimeMillis());
        System.out.println(new Date().getTime());

        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        now = Instant.now();
        System.out.println(now);

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        now = Instant.now();
        System.out.println(now);

    }
}

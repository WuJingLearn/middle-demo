package org.javaboy.utils.logback;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.nio.charset.Charset;

/**
 *
 *
 * Logback日志核心组件:
 *
 * 1.Appender,定义文件输出位置，如ConsoleAppender,FileAppender
 *
 * 2.Layout,Convert 用于定义数据格式，并提供转换,例如，PatternLayout
 *  <Pattern>
 *      %d{HH:mm:ss,SSS} [%thread] %-5level %logger{32} - %msg%n
 *  </Pattern>
 *  其中%d %thread 都对于一个Converter,Converter会将占位符转为具体的值
 *
 *  3.Encoder, Encoder是介于Appender与Layout之间的桥接器。用于将Layout转换好的日志，写入到输出流中
 */
public class LogMessageConverter extends ClassicConverter {

    private static final byte NEW_TRACE_ID_PREFIX = 'v';

    @Override
    public String convert(ILoggingEvent event) {
        if (null != event.getMessage()) {
            return "test";
        }
        return "null";
    }

   /* protected String getPayplusLogUUID() {
        try {
            if (marsRpcAvailable && ServiceKitContext.available()) {
                if (marsTraceIdStringAvailable) {
                    String traceIdString = ServiceKitContext.getTraceIdString();
                    if (null != traceIdString && !traceIdString.equals("")) {
                        return traceIdString;
                    }
                }
                //return byte2hex(ServiceKitContext.getTraceId());
            }
        } catch (Throwable e) {
            //ignore
        }
        return "Non-RPC";
    }

    private static String byte2hex(byte[] bytes) {
        if (bytes[0] == NEW_TRACE_ID_PREFIX) {
            return new String(bytes, Charset.forName("utf-8"));
        }
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex);
        }
        return sign.toString();
    }*/
}
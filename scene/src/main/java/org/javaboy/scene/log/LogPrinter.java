package org.javaboy.scene.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author:majin.wj
 *
 * MDC： 使用%x占位符；
 *
 */
@Component
public class LogPrinter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogPrinter.class);

    @PostConstruct
    public void print(){
        LOGGER.info("hello 大家好");
    }

}

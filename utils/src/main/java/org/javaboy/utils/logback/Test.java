package org.javaboy.utils.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

   static Logger LOG = LoggerFactory.getLogger(Test.class);
    public static void main(String[] args) {
        LOG.info("hello world. ..");

    }
}

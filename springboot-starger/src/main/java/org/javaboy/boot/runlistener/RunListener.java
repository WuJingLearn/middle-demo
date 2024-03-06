package org.javaboy.boot.runlistener;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.stereotype.Component;

/**
 * @author majin.wj
 * @date 2023/6/29 11:38 AM
 * @desc
 * springboot启动时触发，该类不会成为spring的bean
 */
public class RunListener implements SpringApplicationRunListener {


    public RunListener(SpringApplication application, String[] args) {}


        @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        System.out.println("正在启动。...");
    }
}

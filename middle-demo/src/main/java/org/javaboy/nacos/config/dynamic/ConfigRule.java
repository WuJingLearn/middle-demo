package org.javaboy.nacos.config.dynamic;

import com.alibaba.fastjson2.JSON;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executor;

/**
 * @author majin.wj
 * @date 2023/6/15 5:24 PM
 * @desc 使用配置的话，直接注入这个bean就可以使用
 */

@Component
public class ConfigRule implements Listener {

    private String username;
    private String password;

    @Autowired
    ConfigServiceFactory factory;

    @PostConstruct
    public void init() {
        ConfigService configService = factory.getConfigService(false);
        try {
            configService.addListener("config.properties", "app", this);
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Executor getExecutor() {
        return null;
    }

    @Override
    public void receiveConfigInfo(String configInfo) {
        ConfigRule configRule = JSON.parseObject(configInfo, ConfigRule.class);
        this.username = configRule.getUsername();
        this.password = configRule.getPassword();

    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}

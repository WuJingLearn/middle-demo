package org.javaboy.nacos.config.dynamic;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.stereotype.Component;

/**
 * @author majin.wj
 * @date 2023/6/15 5:27 PM
 * @desc
 */
@Component
public class ConfigServiceFactory {

    private ConfigService configService;

    public ConfigServiceFactory() {
        this.configService = createConfigService();
    }

    private ConfigService createConfigService() {
        try {
            return NacosFactory.createConfigService("127.0.0.1:8848");
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }


    public ConfigService getConfigService(boolean create) {
        return create ? createConfigService() : this.configService;
    }

}

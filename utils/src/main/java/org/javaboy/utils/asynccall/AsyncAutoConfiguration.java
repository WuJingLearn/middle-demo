package org.javaboy.utils.asynccall;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.taobao.metaq.client.MetaPushConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @date 2019/08/07
 */
//@ConditionalOnProperty(prefix = "spring.cache.watcher", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(AsyncProperties.class)
@Configuration("com.alibaba.boot.common.async.AsyncAutoConfiguration")
public class AsyncAutoConfiguration {

    private static final String ASYNC_METAQ_CONSUMER_UNITNAME = "async.metaq.consumer.unitname";

    @Autowired
    private Environment environment;

    @Bean
    public AsyncMessageListener asyncMessageListener() {
        return new AsyncMessageListener();
    }

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public MetaPushConsumer asyncMetaPushConsumer(AsyncProperties properties) throws MQClientException {
        MetaPushConsumer consumer = new MetaPushConsumer("CID_syncall_" + properties.getAppName());
        if (environment.getProperty(ASYNC_METAQ_CONSUMER_UNITNAME) != null) {
            consumer.setUnitName(environment.getProperty(ASYNC_METAQ_CONSUMER_UNITNAME));
        }
        consumer.subscribe(properties.getTopic(), properties.getMessageType());
        consumer.setConsumeMessageBatchMaxSize(1);
        consumer.registerMessageListener(asyncMessageListener());
        return consumer;
    }

    @Bean
    public AsyncMethodInterceptor asyncMethodInterceptor() {
        return new AsyncMethodInterceptor();
    }

    @Bean
    public AsyncMethodPointcutAdvisor asyncMethodPointcutAdvisor() {
        return new AsyncMethodPointcutAdvisor();
    }

    @Bean
    public AsyncOperationSource asyncOperationSource() {
        return new AsyncOperationSource();
    }

}

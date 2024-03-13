package org.javaboy.utils.asynccall;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


/**
 * @author chengxl
 * @date 2020/9/8
 */
@ConfigurationProperties(prefix = "spring.asyncall")
@Validated
public class AsyncProperties {

    public static final String CLASS_NAME = "Class";
    public static final String METHOD_NAME = "MethodName";
    public static final String METHOD_SIGNATURE = "MethodSignature";
    public static final String BEAN_NAME = "BeanName";

    private String appName = AsyncUtils.getAppName();

    private String topic = "ASYNC_" + appName;

    private String messageType = "async_call_" + appName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

}

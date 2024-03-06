package org.javaboy.rocket.rocketmq.processor;

import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * @author majin.wj
 * @date 2023/6/26 3:41 PM
 * @desc
 */
public interface MessageProcessor {


    void processMessage(MessageExt msg);

    String tag();

}

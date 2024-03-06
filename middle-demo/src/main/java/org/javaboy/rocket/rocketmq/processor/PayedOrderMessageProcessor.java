package org.javaboy.rocket.rocketmq.processor;

import com.alibaba.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

/**
 * @author majin.wj
 * @date 2023/6/26 3:42 PM
 * @desc
 */
@Component
public class PayedOrderMessageProcessor extends OrderMessageProcessor {

    @Override
    public void processMessage(MessageExt msg) {

    }

    @Override
    public String tag() {
        return "payed";
    }
}

package org.javaboy.rocket.rocketmq.order;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author majin.wj
 * @date 2023/6/26 5:14 PM
 * @desc
 */
@Component
public class OrderMessageListener implements MessageListenerOrderly {

    /**
     * 订单消费者，订阅了整个topic。
     */
    @PostConstruct
    public void init() {
        try {
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
            consumer.setNamesrvAddr("");
            //
            consumer.subscribe("order", "*");
            consumer.registerMessageListener(this);
        } catch (Exception e) {

        }

    }

    @Override
    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
        for (MessageExt msg : msgs) {
            // process
        }
        return ConsumeOrderlyStatus.SUCCESS;
    }
}

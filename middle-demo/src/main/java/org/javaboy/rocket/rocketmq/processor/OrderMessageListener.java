package org.javaboy.rocket.rocketmq.processor;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author majin.wj
 * @date 2023/6/26 3:35 PM
 * @desc 使用一个消费者处理所有的topic，然后收到消息后，根据消息的tag类型，分发到不同的消息processor中。
 * 和使用多个消费者，每个消费者消费某一个tag的情况；
 * <p>
 * 如果使用一个消费者的话，消费的速度会比使用多个消费者要慢。
 */
@Component
public class OrderMessageListener implements MessageListenerConcurrently {

    @Autowired
    private ApplicationContext applicationContext;

    private Map<String, List<OrderMessageProcessor>> processors = new HashMap<>();

    @PostConstruct
    public void init() {

        try {
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
            consumer.setConsumerGroup("order");
            consumer.subscribe("order-topic", "*");
            consumer.registerMessageListener(this);
            consumer.start();
        } catch (MQClientException e) {
            throw new RuntimeException(e);
        }

        Map<String, OrderMessageProcessor> orderProcessors = applicationContext.getBeansOfType(OrderMessageProcessor.class);
        for (OrderMessageProcessor processor : orderProcessors.values()) {
            String tag = processor.tag();
            this.processors.computeIfAbsent(tag, k -> new ArrayList<>()).add(processor);
        }

    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        try {
            for (MessageExt msg : msgs) {
                List<OrderMessageProcessor> processors = this.processors.get(msg.getTags());
                if (!CollectionUtils.isEmpty(processors)) {
                    processors.forEach(processor -> processor.processMessage(msg));
                }
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        } catch (Exception e) {
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
    }
}

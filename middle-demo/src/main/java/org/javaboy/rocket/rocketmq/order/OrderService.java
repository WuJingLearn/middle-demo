package org.javaboy.rocket.rocketmq.order;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

import java.util.List;

/**
 * @author majin.wj
 * @date 2023/6/26 5:07 PM
 * @desc 订单服务
 * 在创建订单和支付订单的时候,虽然创建了不同的tag,但是都发送到了同一个队列中。
 */
public class OrderService {


    DefaultMQProducer producer;


    public void createOrder() throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        Long orderId = 111L;
        Message message = new Message("order", "create", "创建了订单".getBytes());
        try {
            SendResult result = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {

                    int size = mqs.size();
                    Long orderId = (Long) arg;
                    int queueId = (int) (orderId % size);
                    return mqs.get(queueId);
                }
            }, orderId);
        } catch (Exception e) {
            System.out.println("发送消息失败");
        }


    }


    public void payedOrder() {
        // 去支付什么的。
        Long orderId = 111L;
        Message message = new Message("order", "payed", "支付了订单".getBytes());
        try {
            SendResult result = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {

                    int size = mqs.size();
                    Long orderId = (Long) arg;
                    int queueId = (int) (orderId % size);
                    return mqs.get(queueId);
                }
            }, orderId);
        } catch (Exception e) {
            System.out.println("发送消息失败");
        }

    }

    public void cancelOrder() {

    }

}

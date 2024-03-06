package org.javaboy.rocket.rocketmq.producer;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

/**
 * @author:majin.wj
 */
public class Producer {

    public static void main(String[] args) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("1");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        MessageExt messageExt = new MessageExt();
        messageExt.setBody("zs".getBytes());
        messageExt.setTopic("test_topic");

        producer.send(messageExt);
    }

}

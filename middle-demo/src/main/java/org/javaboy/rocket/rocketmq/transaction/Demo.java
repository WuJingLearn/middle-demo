package org.javaboy.rocket.rocketmq.transaction;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.TransactionSendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * @author:majin.wj
 */
public class Demo
{

    /**
     * 事务消息的局限性
     * @param args
     * @throws MQClientException
     */
    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer();
        MessageExt message = new MessageExt();

        TransactionSendResult result = producer.sendMessageInTransaction(message, new LocalTransactionExecuter() {
            @Override
            public LocalTransactionState executeLocalTransactionBranch(Message msg, Object arg) {

                return LocalTransactionState.COMMIT_MESSAGE;
            }
        }, "arg");

    }
}

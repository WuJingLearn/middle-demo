package org.javaboy.rocket.rocketmq.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author:majin.wj 提供消息消费记录功能
 */
@Component
public abstract class MsgRecordSupportListener implements MessageListenerConcurrently {

    /**
     * 最大重重消费次数
     */
    private static final int MAX_RECONSUME_TIME = 16;

    @Autowired
    private MsgRecordRepository msgRecordRepository;

    private ThreadPoolExecutor msgRecordExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors() * 2, 60, TimeUnit.MINUTES, new LinkedBlockingQueue<>(1000));

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        // MessageExt msg = msgs.get(0);
        try {
            for (MessageExt msg : msgs) {
                dealMsg(msg);
            }
            afterProcess(msgs, true);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        } catch (Exception e) {
            afterProcess(msgs, false);
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
    }

    private void afterProcess(List<MessageExt> msgs, boolean processResult) {

        Runnable updateMsgRecord = () -> {
            msgs.forEach(msg -> {
                String topic = msg.getTopic();
                String tags = msg.getTags();
                String msgId = msg.getMsgId();
                byte[] body = msg.getBody();
                MsgRecord msgRecord = msgRecordRepository.queryByMsgId(msgId);
                if (Objects.isNull(msgRecord)) {
                    msgRecordRepository.addMsgRecord(MsgRecord.builder()
                            .topic(topic)
                            .tag(tags)
                            .msgId(msgId)
                            .consumerId(getConsumeId())
                            .content(JSON.toJSONString(body))
                            .consumeStatus(processResult ? "success" : "fail")
                            .consumeTime(1)
                            .gmtCreate(new Date())
                            .gmtModified(new Date())
                            .build());
                } else {
                    msgRecord.setConsumeTime(msgRecord.getConsumeTime() + 1);
                    msgRecord.setConsumeStatus(processResult ? "success" : "fail");
                    msgRecord.setGmtModified(new Date());
                    msgRecordRepository.updateMsgRecordByMsgId(msgRecord);
                }
            });

        };

        msgRecordExecutor.execute(updateMsgRecord);
    }

    protected abstract void dealMsg(MessageExt msg);

    protected abstract String getConsumeId();

    protected int getMaxConsumeTime() {
        return MAX_RECONSUME_TIME;
    }

}


//    private void beforeProcess(MessageExt messageExt) {
//        String topic = messageExt.getTopic();
//        String tags = messageExt.getTags();
//        String msgId = messageExt.getMsgId();
//        byte[] body = messageExt.getBody();
//        MsgRecord msgRecord = msgRecordRepository.queryByMsgId(msgId);
//        if (Objects.isNull(msgRecord)) {
//            msgRecordRepository.addMsgRecord(MsgRecord.builder()
//                    .topic(topic)
//                    .tag(tags)
//                    .msgId(msgId)
//                    .consumerId(getConsumeId())
//                    .content(JSON.toJSONString(body))
//                    .consumeStatus("pending")
//                    .gmtCreate(new Date())
//                    .gmtModified(new Date())
//                    .build());
//        }
//    }

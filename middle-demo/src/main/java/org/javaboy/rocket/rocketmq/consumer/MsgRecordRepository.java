package org.javaboy.rocket.rocketmq.consumer;

/**
 * @author:majin.wj
 */
public interface MsgRecordRepository {

    public MsgRecord queryByMsgId(String msgId);

    boolean addMsgRecord(MsgRecord msgRecord);

    boolean updateMsgRecordByMsgId(MsgRecord msgRecord);

}

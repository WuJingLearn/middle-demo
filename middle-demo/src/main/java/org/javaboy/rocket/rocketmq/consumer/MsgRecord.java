package org.javaboy.rocket.rocketmq.consumer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author:majin.wj
 */
@Builder
@Setter
@Getter
public class MsgRecord {

    private Date gmtCreate;
    private Date gmtModified;
    private String msgId;
    private String topic;
    private String tag;
    private String consumerId;
    private String content;
    private String consumeStatus;
    private Integer consumeTime;
    private String notifyStatus;

}

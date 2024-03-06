package org.javaboy.nacos.ext.event.simple.factory;

import org.javaboy.nacos.ext.event.simple.Event;
import org.javaboy.nacos.ext.event.simple.EventPublisher;

/**
 * @author majin.wj
 * @date 2023/6/18 10:16 PM
 * @desc
 */
public interface EventPublishFactory {


    EventPublisher createEventPublisher(Class<? extends Event> type, int bufferSize);

}

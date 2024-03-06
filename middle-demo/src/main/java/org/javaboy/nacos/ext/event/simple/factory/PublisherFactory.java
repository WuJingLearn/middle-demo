package org.javaboy.nacos.ext.event.simple.factory;

import org.javaboy.nacos.ext.event.simple.Event;
import org.javaboy.nacos.ext.event.simple.EventPublisher;
import org.javaboy.nacos.ext.event.simple.Publisher;

/**
 * @author majin.wj
 * @date 2023/6/18 10:10 PM
 * @desc
 */
public class PublisherFactory implements EventPublishFactory{

    @Override
    public EventPublisher createEventPublisher(Class<? extends Event> type,int bufferSize) {
        EventPublisher publisher = new Publisher();
        publisher.init(type,bufferSize);
        return publisher;
    }
}

package org.javaboy.nacos.ext.event.simple;

import org.javaboy.nacos.ext.event.simple.factory.EventPublishFactory;
import org.javaboy.nacos.ext.event.simple.factory.PublisherFactory;
import org.javaboy.nacos.ext.event.simple.listener.Subscriber;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author majin.wj
 * @date 2023/6/18 7:42 PM
 * @desc
 */
public class NotifyCenter {


    public static final NotifyCenter INSTANCE = new NotifyCenter();

    private EventPublishFactory factory = new PublisherFactory();

    private Integer bufferSize = 10000;

    /**
     * 事件类型/publisher
     */
    public Map<Class<?>, EventPublisher> publisherMap = new ConcurrentHashMap<>();


    public static void publish(Event event) {
        EventPublisher publisher = INSTANCE.computeIfAbsentPublisher(event.getClass());
        publisher.publish(event);
    }

    private EventPublisher computeIfAbsentPublisher(Class<? extends Event> cls) {
        return publisherMap.computeIfAbsent(cls, t -> factory.createEventPublisher(cls, bufferSize));
    }


    public static void registerSubscriber(Subscriber subscriber) {
        List<Class<? extends Event>> subscribeTypes = subscriber.getSubscribeTypes();
        if (!CollectionUtils.isEmpty(subscribeTypes)) {
            for (Class<? extends Event> subscribeType : subscribeTypes) {
                EventPublisher publisher = INSTANCE.computeIfAbsentPublisher(subscribeType);
                publisher.addSubscriber(subscriber);
            }
        }
    }

}

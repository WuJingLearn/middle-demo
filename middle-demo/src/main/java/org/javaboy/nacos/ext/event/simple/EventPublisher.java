package org.javaboy.nacos.ext.event.simple;

import org.javaboy.nacos.ext.event.simple.listener.Subscriber;

/**
 * @author majin.wj
 * @date 2023/6/18 9:54 PM
 * @desc
 */
public interface EventPublisher {

    void init(Class<? extends Event> type, int bufferSize);

    boolean publish(Event event);

    void addSubscriber(Subscriber subscriber);

    void removeSubscriber(Subscriber subscriber);


}

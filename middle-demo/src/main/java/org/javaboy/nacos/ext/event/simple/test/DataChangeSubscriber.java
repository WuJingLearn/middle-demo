package org.javaboy.nacos.ext.event.simple.test;

import org.javaboy.nacos.ext.event.simple.Event;
import org.javaboy.nacos.ext.event.simple.NotifyCenter;
import org.javaboy.nacos.ext.event.simple.listener.Subscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * @author majin.wj
 * @date 2023/6/18 7:38 PM
 * @desc
 */
public class DataChangeSubscriber implements Subscriber {


    public DataChangeSubscriber() {
        NotifyCenter.registerSubscriber(this);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof DataEvent) {
            System.out.println("收到dataEvent:" + event.getClass());
        } else if (event instanceof DataLoadEvent) {
            System.out.println("收到dataLoadEvent:" + event.getClass());
        }
    }

    @Override
    public List<Class<? extends Event>> getSubscribeTypes() {
        List<Class<? extends Event>> eventTypes = new ArrayList<>();
        eventTypes.add(DataEvent.class);
        eventTypes.add(DataLoadEvent.class);
        return eventTypes;
    }
}

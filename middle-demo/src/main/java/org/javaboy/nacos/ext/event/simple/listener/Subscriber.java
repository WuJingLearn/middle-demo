package org.javaboy.nacos.ext.event.simple.listener;

import org.javaboy.nacos.ext.event.simple.Event;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author majin.wj
 * @date 2023/6/18 6:45 PM
 * @desc
 */
public interface Subscriber {

    void onEvent(Event msg);

    List<Class<? extends Event>> getSubscribeTypes();

    default Executor getExecutor() {
        return null;
    }

}

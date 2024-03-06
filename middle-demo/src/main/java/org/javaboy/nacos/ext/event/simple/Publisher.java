package org.javaboy.nacos.ext.event.simple;

import org.javaboy.nacos.ext.event.simple.listener.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author majin.wj
 * @date 2023/6/18 6:45 PM
 * @desc
 */
public class Publisher extends Thread implements EventPublisher {


    private volatile boolean shutdown = false;

    private BlockingQueue<Event> queue = new LinkedBlockingQueue<>();
    List<Subscriber> subscribers = new ArrayList<>();

    public void init(Class<? extends Event> type, int bufferSize) {
        String eventType = type.getName();
        this.queue = new ArrayBlockingQueue<>(bufferSize);

        super.setName("publisher-" + eventType);
        super.setDaemon(true);
        super.start();
    }


    public boolean publish(Event event) {
        boolean success = queue.offer(event);
        if (!success) {
            // 同步发送
            handleEvent(event);
        }
        return success;
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        this.subscribers.remove(subscriber);
    }

    @Override
    public void run() {

        while (!shutdown) {
            try {
                Event event = queue.take();
                handleEvent(event);
            } catch (InterruptedException e) {
                System.out.println("TakeEvent failed" + e);
                Thread.currentThread().interrupt();
            }
        }
    }

    private void handleEvent(Event event) {
        for (Subscriber subscriber : subscribers) {
            notifySubscriber(event, subscriber);
        }
    }

    private void notifySubscriber(Event event, Subscriber subscriber) {
        Runnable runnable = () -> subscriber.onEvent(event);
        Executor executor = subscriber.getExecutor();
        if (executor != null) {
            executor.execute(runnable);
        } else {
            runnable.run();
        }

    }

}

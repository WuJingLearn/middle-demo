package org.javaboy.nacos.ext.event.simple.test;

import org.javaboy.nacos.ext.event.simple.Event;
import org.javaboy.nacos.ext.event.simple.NotifyCenter;
import org.javaboy.nacos.ext.event.simple.Publisher;
import org.javaboy.nacos.ext.event.simple.listener.Subscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * @author majin.wj
 * @date 2023/6/18 7:04 PM
 * @desc
 * 1.直接使用订阅者，会让业务执行与监听者执行强耦合，并且这是一个同步的执行；更加优雅的执行方式是通过消息/事件来进行一个解藕。
 *
 * 2.在业务代码中直接使用Publisher发布者，然后为发布者构建订阅者。使用publisher来发布事件。
 * 可以看到，使用起来不太灵活，需要每次够自己构建publisher,已经手动注册订阅者。
 *
 *
 *
 */
public class GameCenter {

    List<Subscriber> subscribers = new ArrayList<>();

    private Publisher publisher = new Publisher();


    public GameCenter(){
        publisher.addSubscriber(new DataChangeSubscriber());
    }


    public void startGame(){
        // type1 直接使用订阅者，强耦合
        for (Subscriber subscriber : subscribers) {
            Event event = new Event();
            subscriber.onEvent(event);
        }
    }

    public void endGame(){
        // type2. 发布一个事件，立即返回，将事件放入到了阻塞队列中。
        publisher.publish(new Event());
    }


    public static void initApplication(){
        // 项目启动时，初始化订阅者,
        DataChangeSubscriber dataChangeSubscriber = new DataChangeSubscriber();
    }

    public static void stopGame(){
        // 直接使用通知中心发送一个事件。
        NotifyCenter.publish(new DataEvent());
        NotifyCenter.publish(new DataLoadEvent());
    }

    public static void main(String[] args) {
        initApplication();
        stopGame();
    }

}

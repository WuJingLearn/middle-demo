package org.javaboy.rocket.rocketmq.consumer;

import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.lang.reflect.Method;

/**
 * @author:majin.wj
 */
public class Test {


    public static void main(String[] args) {
        try {
            MessageExt messageExt = new MessageExt();
            Method method = Message.class.getDeclaredMethod("putProperty", String.class, String.class);
            method.setAccessible(true);
            try {
                method.invoke(messageExt, "name", "age");
            } catch (Exception e) {
               ;
            }
            System.out.println(messageExt.getProperty("name"));
        } catch (NoSuchMethodException e) {

        }

    }

}

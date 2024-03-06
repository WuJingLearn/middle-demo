package org.javaboy.nacos.ext.listener;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author majin.wj
 * @date 2023/6/18 3:51 PM
 * @desc
 */
public class Button {


    private List<Listener> listeners = new ArrayList<>();

    public void click(String button) {
        if (!CollectionUtils.isEmpty(listeners)) {
            for (Listener listener : listeners) {
                System.out.println("敲击了" + button + "按钮");
                listener.notify(button);
            }
        }
    }

    public void registerListener(Listener listener){
        this.listeners.add(listener);
    }

    public static void main(String[] args) {

        Button button = new Button();
        button.registerListener(new Listener() {
            @Override
            public void notify(Object data) {
                String click = (String) data;
                switch (click) {
                    case "quit":
                        System.out.println("退出程序");
                        break;
                    case "begin":
                        System.out.println("开始程序");
                        break;
                    default:
                        System.out.println("位置操作");
                        break;
                }
            }
        });


         button.click("quit");
         button.click("begin");
         button.click("hello");

    }

}

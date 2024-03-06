package org.javaboy.adapter.handleradapter;

import java.util.ArrayList;
import java.util.List;

public class DispatcherServlet {


   private List<HandlerAdapter> handlerAdapters = new ArrayList<>();


    /**
     * 通过处理器找到具体的适配器。需要通过适配器才能具体执行.
     * 找适配器的过程类似于一个策略模式；
     * @param handler
     * @return
     */
   public HandlerAdapter findHandlerAdapter(Object handler) {
       if(handlerAdapters!=null) {
           for (HandlerAdapter handlerAdapter : handlerAdapters) {
               if(handlerAdapter.supports(handler)){
                   return handlerAdapter;
               }
           }
       }
       throw new RuntimeException("Can't find handlerAdapter");

   }

}

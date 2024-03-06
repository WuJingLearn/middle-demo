package org.javaboy.commond.base;

/**
 * 命令模式的定义：
 * 将一个请求封装为一个对象，，简单来说，就是为了将函数方法封装为对象以方便传输；
 * 有点类似于函数时编程
 * 简单来说，命令模式的本质是对命令进行封装，将发出命令的责任和执行命令的责任分离开。
 * Callable call = ()->{}；
 */
public class Main {
    public static void main(String[] args) {
        Html5Editor html5Editor = new Html5Editor();
        MarkDownEditor markDownEditor = new MarkDownEditor();
        Open html5Open = new Open(html5Editor);
        Save html5Save = new Save(html5Editor);
        Close html5Close = new Close(html5Editor);
        Open markDownOpen = new Open(markDownEditor);
        Save markDownSave = new Save(markDownEditor);
        Close markDownClose = new Close(markDownEditor);
        WebEditFlow webEditFlow = new WebEditFlow();
        webEditFlow.setCommand(html5Open);
        webEditFlow.setCommand(html5Save);
        webEditFlow.setCommand(html5Close);
        webEditFlow.setCommand(markDownOpen);
        webEditFlow.setCommand(markDownSave);
        webEditFlow.setCommand(markDownClose);
        webEditFlow.run();
    }
}

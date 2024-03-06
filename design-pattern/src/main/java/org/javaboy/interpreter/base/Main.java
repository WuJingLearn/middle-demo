package org.javaboy.interpreter.base;

/**
 * 解释器模式，它用来描述如何构建一个简单的“语言”解释器。比起命令
 * 模式，解释器模式更加小众，只在一些特定的领域会被用到，比如编译器、规则引擎、正则
 * 表达式，sql解析等。不过，了解它的实现原理同样很重要，能帮助你思考如何通过更简洁的规则来表示复杂的逻辑。
 *
 * 解释器模式:
 * 为某个语言定义语法规则，并定义了一个解释器来处理这个语法
 * 这个语言，在代码里就是某种语法规则
 *
 *
 */
public class Main {
    public static void main(String[] args) {
        Expression person1 = new TerminalExpression("mick");
        Expression person2 = new TerminalExpression("mia");
        Expression isSingle = new OrExpression(person1, person2);

        Expression spike = new TerminalExpression("spike");
        Expression mock = new TerminalExpression("mock");
        Expression isCommitted = new AndExpression(spike, mock);

        System.out.println(isSingle.interpreter("mick"));
        System.out.println(isSingle.interpreter("mia"));
        System.out.println(isSingle.interpreter("max"));
        System.out.println(isCommitted.interpreter("mock, spike"));
        System.out.println(isCommitted.interpreter("Single, mock"));
    }
}

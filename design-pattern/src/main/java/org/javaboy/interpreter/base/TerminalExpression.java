package org.javaboy.interpreter.base;

/**
 * 定义一个终结符表达式类 TerminalExpression 作为主解释器
 */
public class TerminalExpression implements Expression{
    String data;
    public TerminalExpression(String data) {
        this.data = data;
    }
    @Override
    public boolean interpreter(String con) {
        return con.contains(data);
    }
}
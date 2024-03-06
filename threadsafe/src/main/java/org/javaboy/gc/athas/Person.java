package org.javaboy.gc.athas;

/**
 * @author:majin.wj
 */
public class Person {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void hello(String msg){
        System.out.println("hello"+msg);
    }
}

package com.example.spring.web.tomcat.mvc;

/**
 * @author:majin.wj
 */
public class Result <T>{

    T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                '}';
    }
}

package org.javaboy.mybatis.example.interceptors;

/**
 * @author:majin.wj
 */
public class Test {

   static  <T> T select(String id) {
       return (T)select();
   }

   static Object select(){
       return null;
   }
}

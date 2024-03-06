package org.javaboy.builder.base;

import lombok.Builder;
import lombok.Data;

@Data
// 加了builder之后，就无法使用无参数构造器

@Builder
public class Student {

  /*  public Student(){

    }*/

    private String name;
    private Integer age;


    /**
     *
     * 加了Builder注解之后，相当于增加了如下代码
     *
     *  class Example<T> {
     *   	private T foo;
     *   	private final String bar;
     *
     *   	private Example(T foo, String bar) {
     *   		this.foo = foo;
     *   		this.bar = bar;
     *        }
     *
     *   	public static <T> ExampleBuilder<T> builder() {
     *   		return new ExampleBuilder<T>();
     *    }
     *
     *   	public static class ExampleBuilder<T> {
     *   		private T foo;
     *   		private String bar;
     *
     *   		private ExampleBuilder() {}
     *
     *   		public ExampleBuilder foo(T foo) {
     *   			this.foo = foo;
     *   			return this;
     *        }
     *
     *   		public ExampleBuilder bar(String bar) {
     *   			this.bar = bar;
     *   			return this;
     *        }
     *
     *        @java.lang.Override public String toString() {
     *   			return "ExampleBuilder(foo = " + foo + ", bar = " + bar + ")";
     *        }
     *
     *   		public Example build() {
     *   			return new Example(foo, bar);
     *        }
     *    }
     *   }
     */
}

package org.javaboy.decorator.base;

/**
 * 装饰器模式：
 * 装饰器模式和适配器模式很像,都是使用组合关系的方式来实现。装饰器侧重于扩展类的功能。适配器侧重于转换类。
 *
 * 装饰器模式适用于功能需要被扩展 ，而又不想继承原有类的场景。
 * 装饰器对象不能太多，定位问题比较负责
 */
public class Main {

    public static void main(String[] args) {


        FileUploader uploader = new FileCompressDecorator(new FileUploadImpl());

        uploader.upload("peace and love");

    }

}

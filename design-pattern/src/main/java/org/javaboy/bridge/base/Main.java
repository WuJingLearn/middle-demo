package org.javaboy.bridge.base;

/**
 * 桥接模式：将抽象实体和抽象行为分离
 *
 * 桥接模式原理的核心是抽象与抽象之间的分离，这样分离的好处就在于，
 * 具体的实现类依赖抽象而不是依赖具体，满足 DIP 原则，很好地完成了对象结构间的解耦。
 * 换句话说，抽象的分离间接完成了具体类与具体类之间的解耦，它们之间使用抽象来进行组合或聚合，而不再使用继承。
 */
public class Main {


    /**
     * 将文件上传器和文件上传行为进行分离
     * 实现实体和行为的灵活组合
     * @param args
     */
    public static void main(String[] args) {
        FileUploadExecutor executor = new LinuxFileUploadExecutor();
        FileUploaderImpl uploader = new FileUploaderImpl(executor);
    }

}

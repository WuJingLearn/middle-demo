package org.javaboy.visitor.base;

import org.javaboy.visitor.demo1.File;

import java.util.ArrayList;
import java.util.List;

/**
 * 访问者模式定义：
 * 允许在运行时将一个或多个操作应用于一组对象，将操作与对象结构分离。
 * 访问者模式核心关注点是分离一组对象结构和对象的操作，对象结构可以各不相同，但必须以某一个或一组操作作为连接的中心点。
 *
 *
 *
 * 我感觉访问者模式有点多余，
 * 访问者的本质是方法的实现逻辑，通过具体的访问者来实现,
 * 即使不使用访问者依然可以完成，下面的实现就是通过策略模式来定义对不同的文件类型的处理。
 *
 * 压缩文件：定义了Text和World类型的两种压缩算法
 * 打印文件：定义了Text和World类型的两种打印算法。
 * 然后可以创建两个工厂模式来获取具体操作文件类。
 *
 * 但是当对文件的操作有很多种时，不仅是压缩，打印，如果还有编码，等操作的话，使用策略+工厂模式就会
 * 创建很多类。
 * 此时就可以使用访问者的方式来完成。
 * 1.压缩访问者，定义了text文件压缩，word文件压缩，pdf文件压缩的方法
 * 2.打印访问者，定义了text文件打印，word文件打印，prd文打印的方式
 *
 * File类中定义具体操作的方法，然后方法中包含Visitor访问者类型来具体操作。
 * 压缩方法，打印方法，传递不同的visitor对象。
 *
 *
 */
public class Main {


    interface FileCompressService {
        void compress(File file);

    }

    class WordFileCompressService implements FileCompressService {

        @Override
        public void compress(File file) {
            System.out.println("word 文件压缩");
        }
    }


    class TextFileCompressService implements FileCompressService{

        @Override
        public void compress(File file) {
            System.out.println("text文件压缩");
        }
    }

    interface FilePrintService {
        void print(File file);
    }

    class TextFilePrintService implements FilePrintService{

        @Override
        public void print(File file) {
            System.out.println("text文件打印");
        }
    }

    class WordFilePrintService implements FilePrintService{

        @Override
        public void print(File file) {
            System.out.println("word文件打印");
        }
    }

    void test(){

        List<File> fileList = new ArrayList<>();

        for (File file : fileList) {
            // 根据文件类型获得具体的服务，进行处理，这是策略模式的实现。
            FileCompressService compressService;
            FilePrintService filePrintService;
        }


    }


    public static void main(String[] args) {



    }

}

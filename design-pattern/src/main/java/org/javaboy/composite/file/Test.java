package org.javaboy.composite.file;

public class Test {

        public static void main(String[] args) {

            String path = "/Users/jingmac/IdeaProjects/middleware-sample/design-pattern/src/main/java/org/javaboy/composite/file/test";
            FileDirectory fileDirectory = new FileDirectory(path);

            FileDirectory file1 = new FileDirectory(path + "/file1");
            FileDirectory file2 = new FileDirectory(path + "/file2");

            fileDirectory.addChildNode(file1);
            fileDirectory.addChildNode(file2);

            FileText fileText1 = new FileText(file1.filePath() + "/1.txt");
            FileText fileText2 = new FileText(file2.filePath() + "/2.txt");

            file1.addChildNode(fileText1);
            file2.addChildNode(fileText2);


            long fileSize = fileDirectory.getFileSize();
            int fileCount = fileDirectory.getFileCount();
            System.out.println(fileSize);
            System.out.println(fileCount);

        }
}

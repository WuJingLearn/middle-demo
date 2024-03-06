package org.javaboy.scene.directory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author:majin.wj
 */
public class DirectoryMemoryStorage implements DirectoryStorage{

    private final List<Directory> directoryList = new ArrayList<>();


    private AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public void createDirectory(String name, Long parentId, Integer order) {
        Directory directory = new Directory();
        directory.setId(idGenerator.getAndIncrement());
        directory.setName(name);
        directory.setPid(parentId);
        directory.setOrder(order);
    }

    @Override
    public List<Directory> queryAllDirectory() {
        return Collections.unmodifiableList(directoryList);
    }

    public static void main(String[] args) {
        List<Directory> list = new ArrayList<>();

        Directory directory = new Directory();
        directory.setId(1L);
        directory.setName("zs");
        directory.setPid(0L);
        directory.setOrder(1);
        list.add(directory);

        List<Directory> directories = Collections.unmodifiableList(list);
        Directory directory1 = directories.get(0);
        directory1.setOrder(2);

        System.out.println(directory1);
        System.out.println(directory);
    }
}

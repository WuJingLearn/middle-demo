package org.javaboy.scene.directory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author:majin.wj
 */
public class DirectoryService {


    private DirectoryStorage directoryStorage;

    public DirectoryService() {
        this.initDirectory();
        ;
    }

    public void initDirectory() {
        //id:1
        directoryStorage.createDirectory("编程语言", 0L, 1);

        //id：2
        directoryStorage.createDirectory("烹饪", 0L, 2);


        directoryStorage.createDirectory("java", 1L, 1);
        directoryStorage.createDirectory("js", 1L, 2);
    }


    public DirectoryHierarchy queryAllDirectory() {
        DirectoryHierarchy directoryHierarchy = new DirectoryHierarchy();
        directoryHierarchy.setName("根目录");
        directoryHierarchy.setId(0L);
        List<Directory> directories = directoryStorage.queryAllDirectory();
        List<Directory> firstLevelDirectors = directories.stream().filter(d -> d.getPid() == 0L).sorted((d1, d2) -> {
            return Integer.compare(d1.getOrder(), d2.getOrder());
        }).collect(Collectors.toList());
//        directoryHierarchy.setChildren(directories);

        return null;
    }


    public void setDirectoryStorage(DirectoryStorage directoryStorage) {
        this.directoryStorage = directoryStorage;
    }
}

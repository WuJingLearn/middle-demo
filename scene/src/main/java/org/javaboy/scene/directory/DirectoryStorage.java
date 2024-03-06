package org.javaboy.scene.directory;

import java.util.List;

/**
 * @author:majin.wj
 */
public interface DirectoryStorage {


    void createDirectory(String name,Long parentId,Integer order);


    List<Directory> queryAllDirectory();


}

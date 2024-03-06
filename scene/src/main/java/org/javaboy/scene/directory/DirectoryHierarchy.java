package org.javaboy.scene.directory;

import java.util.List;

/**
 * @author:majin.wj
 */
public class DirectoryHierarchy extends Directory{

    private List<DirectoryHierarchy> children;


    public void setChildren(List<DirectoryHierarchy> children) {
        this.children = children;
    }

    public List<DirectoryHierarchy> getChildren() {
        return children;
    }
}

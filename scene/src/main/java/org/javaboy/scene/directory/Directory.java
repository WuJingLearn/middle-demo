package org.javaboy.scene.directory;

import lombok.Data;

/**
 * @author:majin.wj
 */
@Data
public class Directory {

    /**
     * 目录id
     */
    private Long id;

    /**
     * 目录名称
     */
    private String name;

    /**
     * 同一层排序
     */
    private Integer order;

    /**
     * 父id
     */
    private Long pid;



}

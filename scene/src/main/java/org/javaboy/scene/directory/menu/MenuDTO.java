package org.javaboy.scene.directory.menu;

import lombok.Data;

import java.util.List;

/**
 * @author:majin.wj
 */
@Data
public class MenuDTO {
    private Integer id;
    private String name;
    private Integer order;

    private List<MenuDTO> children;
}

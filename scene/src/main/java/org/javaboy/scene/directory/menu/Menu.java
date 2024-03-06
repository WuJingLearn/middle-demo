package org.javaboy.scene.directory.menu;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:majin.wj
 */
@Data
public class Menu {

    private Integer id;
    private String name;
    private Integer order;
    private Integer pid;

    private static List<Menu> memuList = new ArrayList<>();

    public static void main(String[] args) {

    }

    public static void init() {
        Menu menu = new Menu();
        menu.setOrder(0);
        menu.setId(0);
        menu.setPid(-1);
        menu.setName("root");

        Menu javaMenu = new Menu();
        javaMenu.setId(1);
        javaMenu.setName("java");
        javaMenu.setPid(0);
        javaMenu.setOrder(1);

        Menu cMenu = new Menu();
        cMenu.setId(2);
        cMenu.setName("c");
        cMenu.setPid(0);
        cMenu.setOrder(2);

        memuList.add(menu);
        memuList.add(javaMenu);
        memuList.add(cMenu);
    }

}

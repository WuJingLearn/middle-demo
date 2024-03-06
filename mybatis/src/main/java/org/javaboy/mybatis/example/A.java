package org.javaboy.mybatis.example;



/**
 * @author majin.wj
 * @date 2023/4/27 10:10 AM
 * @desc
 */

public class A {
    private String name;
    private Integer score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}

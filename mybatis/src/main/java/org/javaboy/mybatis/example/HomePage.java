package org.javaboy.mybatis.example;

/**
 * @author:majin.wj
 */
public class HomePage {
    private Long id;
    private String homePage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePAge(String homePAge) {
        this.homePage = homePAge;
    }

    @Override
    public String toString() {
        return "HomePage{" +
                "id=" + id +
                ", homePAge='" + homePage + '\'' +
                '}';
    }
}

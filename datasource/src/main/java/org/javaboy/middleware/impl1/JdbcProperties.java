package org.javaboy.middleware.impl1;

/**
 * @author majin.wj
 * @date 2023/5/9 3:49 PM
 * @desc
 */
public class JdbcProperties {
    private String url;
    private String username;
    private String password;

    public JdbcProperties(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

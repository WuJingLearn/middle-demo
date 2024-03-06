package practice.builder;


/**
 * @author:majin.wj
 *
 */
public class ActivityCreateRequest {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ActivityCreateRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

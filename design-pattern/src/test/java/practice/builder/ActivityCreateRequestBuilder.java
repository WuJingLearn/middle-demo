package practice.builder;

import lombok.Data;

/**
 * @author:majin.wj
 */
public class ActivityCreateRequestBuilder {

    private Integer id;
    private String name;

    public static ActivityCreateRequestBuilder newBuilder(){
        return new ActivityCreateRequestBuilder();
    }

    public ActivityCreateRequestBuilder id(Integer id){
        this.id = id;
        return this;
    }


    public ActivityCreateRequestBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ActivityCreateRequest build(){
        ActivityCreateRequest activityCreateRequest = new ActivityCreateRequest();
        activityCreateRequest.setId(id);
        activityCreateRequest.setName(name);
        return activityCreateRequest;
    }

}

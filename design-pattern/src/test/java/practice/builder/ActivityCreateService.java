package practice.builder;

/**
 * @author:majin.wj
 */
public class ActivityCreateService {


    public void create(ActivityCreateRequest request){
        System.out.println("收到创建请求:"+request);
    }


    public static void main(String[] args) {
        ActivityCreateRequestBuilder builder = ActivityCreateRequestBuilder.newBuilder().id(1).name("zs");
        ActivityCreateRequest request = builder.build();

        ActivityCreateService activityCreateService = new ActivityCreateService();
        activityCreateService.create(request);




    }
}

package org.javaboy.mediator.home;

import javax.annotation.Resource;

/**
 * @author majin.wj
 * @date 2023/6/28 10:07 PM
 * @desc
 */
public class Zuke implements HomeInfoListener{


    @Resource
    HomeMediator homeMediator;


    public void getHome() {

        GetHomeRquest getHomeRquest = new GetHomeRquest();

        homeMediator.getHome(getHomeRquest,this);
    }


    @Override
    public void notify(Home home) {
        System.out.println("有新的房源信息");

        getHome();

    }
}

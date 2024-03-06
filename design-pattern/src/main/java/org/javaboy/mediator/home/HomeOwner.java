package org.javaboy.mediator.home;

import javax.annotation.Resource;

/**
 * @author majin.wj
 * @date 2023/6/28 10:04 PM
 * @desc
 */
public class HomeOwner implements Owner {


    @Resource
    HomeMediator mediator;

    @Override
    public void publishHome(Home home) {
        mediator.publishHome(home);
    }
}

package org.javaboy.mybatis.example;

import org.javaboy.mybatis.example.transaction.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.AbstractSet;
import java.util.HashMap;
import java.util.List;

/**
 * @author majin.wj
 * @date 2023/6/9 4:52 PM
 * @desc
 */
@Component
public class TestBean {


    @Autowired
    private AService aService;

    @Autowired
    private HomePageMaapper homePageMaapper;


    @PostConstruct
    public void  init() {
        aService.test();;
    }



}

package org.javaboy.mybatis.example.transaction;

import org.javaboy.mybatis.example.A;
import org.javaboy.mybatis.example.AMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author:majin.wj
 */
@Service
public class BService {


    @Autowired
    AMapper aMapper;


    /**
     * 事务方法，并不是加锁，这里依然可以多线程并发访问，
     * 只是在执行insert的时候，会有数据库的悲观锁
     */
    @Transactional
    public void test() {
        A a = new A();
        a.setScore(100);
        List<A> select = aMapper.select(a);
        System.out.println(select);
    }


}

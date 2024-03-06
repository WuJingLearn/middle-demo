package org.javaboy.mybatis.source.init;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class StartMain {


    public static void main(String[] args) {
        InputStream in = null;
        SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                .build(in);
        SqlSession sqlSession = factory.openSession();

    }

}

package org.javaboy.mybatis.source.typehandler;

import java.sql.ResultSet;

/**
 * TypeHandler 用来处理java和jdbc类型的转换：
 * 就是处理prepareStatement.setBoolean(i,true）这样的东西；
 * 在执行sql的时候，需要通过类型处理器，来填充数据；在处理结果时也需要通过类型处理器来获得结果
 * TypeHandlerRegistry： 类型处理器注册
 *
 *
 * TypeAliasRegistry：别名的注册
 *

 */
public class Test {
    public static void main(String[] args) {
        ResultSet set;
//        Blob blob = set.getBlob(1);
//        Clob clob = set.getClob(1);
    }

}

package org.javaboy.mybatis.example;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author majin.wj
 * @date 2023/5/4 9:55 PM
 * @desc
 *
 * 常用的sql标签：
 * select update insert delete
 * <sql></sql> <include></include>
 *
 * <where></where> <trim></trim> <if></if> <foreach></foreach>
 * <set></set>
 *
 * 一次sql执行流程:
 * DefaultSqlSession.selectList->.CachingExecutor.query->BaseExecutor.query->SimpleExecutor.doQuery
 * ->RoutingStatementHandler.query->PreparedStatementHandler.query---> 数据源层DruidPooledPreparedStatement.execute
 * ->mysql层 ClientPreparedStatement.execute
 */
public interface AMapper {

    List<A> selectByScore(int score);

    List<A> select(A aParam);

    int addOne(A a);

    int update(A a);

    int updateByScore(A a);

    int insert(A a);

    int deleteByName(String name);

    /**
     * 根据searchKey进行查询，比如只在一个输入框，输入name或者score 都可以查询到数据
     * @param param
     * @return
     */
    List<A> search(Map<String,Object> param);


    /*不需要映射的sql语句，直接使用注解即可*/
//    @Select("select * from a where score= #{score}")
//    List<A> select(int score);



}

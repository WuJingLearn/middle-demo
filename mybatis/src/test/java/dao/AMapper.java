package dao;

import dataobject.A;
import dataobject.AExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a
     *
     * @mbggenerated Sun Dec 10 11:13:22 CST 2023
     */
    int countByExample(AExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a
     *
     * @mbggenerated Sun Dec 10 11:13:22 CST 2023
     */
    int deleteByExample(AExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a
     *
     * @mbggenerated Sun Dec 10 11:13:22 CST 2023
     */
    int insert(A record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a
     *
     * @mbggenerated Sun Dec 10 11:13:22 CST 2023
     */
    int insertSelective(A record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a
     *
     * @mbggenerated Sun Dec 10 11:13:22 CST 2023
     */
    List<A> selectByExample(AExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a
     *
     * @mbggenerated Sun Dec 10 11:13:22 CST 2023
     */
    int updateByExampleSelective(@Param("record") A record, @Param("example") AExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a
     *
     * @mbggenerated Sun Dec 10 11:13:22 CST 2023
     */
    int updateByExample(@Param("record") A record, @Param("example") AExample example);
}
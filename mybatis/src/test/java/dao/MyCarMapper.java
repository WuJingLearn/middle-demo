package dao;

import dataobject.MyCar;
import dataobject.MyCarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyCarMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MyCar
     *
     * @mbggenerated Sun Dec 10 11:16:23 CST 2023
     */
    int countByExample(MyCarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MyCar
     *
     * @mbggenerated Sun Dec 10 11:16:23 CST 2023
     */
    int deleteByExample(MyCarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MyCar
     *
     * @mbggenerated Sun Dec 10 11:16:23 CST 2023
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MyCar
     *
     * @mbggenerated Sun Dec 10 11:16:23 CST 2023
     */
    int insert(MyCar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MyCar
     *
     * @mbggenerated Sun Dec 10 11:16:23 CST 2023
     */
    int insertSelective(MyCar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MyCar
     *
     * @mbggenerated Sun Dec 10 11:16:23 CST 2023
     */
    List<MyCar> selectByExample(MyCarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MyCar
     *
     * @mbggenerated Sun Dec 10 11:16:23 CST 2023
     */
    MyCar selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MyCar
     *
     * @mbggenerated Sun Dec 10 11:16:23 CST 2023
     */
    int updateByExampleSelective(@Param("record") MyCar record, @Param("example") MyCarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MyCar
     *
     * @mbggenerated Sun Dec 10 11:16:23 CST 2023
     */
    int updateByExample(@Param("record") MyCar record, @Param("example") MyCarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MyCar
     *
     * @mbggenerated Sun Dec 10 11:16:23 CST 2023
     */
    int updateByPrimaryKeySelective(MyCar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MyCar
     *
     * @mbggenerated Sun Dec 10 11:16:23 CST 2023
     */
    int updateByPrimaryKey(MyCar record);
}
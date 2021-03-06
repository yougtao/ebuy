package com.yongtao.ebuy.manager.mapper;

import com.yongtao.ebuy.manager.pojo.ItemParam;
import com.yongtao.ebuy.manager.pojo.ItemParamExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemParamMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_param
     *
     * @mbggenerated
     */
    int countByExample(ItemParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_param
     *
     * @mbggenerated
     */
    int deleteByExample(ItemParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_param
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_param
     *
     * @mbggenerated
     */
    int insert(ItemParam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_param
     *
     * @mbggenerated
     */
    int insertSelective(ItemParam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_param
     *
     * @mbggenerated
     */
    List<ItemParam> selectByExampleWithBLOBs(ItemParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_param
     *
     * @mbggenerated
     */
    List<ItemParam> selectByExample(ItemParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_param
     *
     * @mbggenerated
     */
    ItemParam selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_param
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") ItemParam record, @Param("example") ItemParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_param
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") ItemParam record, @Param("example") ItemParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_param
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") ItemParam record, @Param("example") ItemParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_param
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ItemParam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_param
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(ItemParam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_param
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ItemParam record);
}
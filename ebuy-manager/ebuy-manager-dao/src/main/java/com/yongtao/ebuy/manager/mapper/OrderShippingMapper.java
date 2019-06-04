package com.yongtao.ebuy.manager.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.yongtao.ebuy.manager.pojo.OrderShipping;
import com.yongtao.ebuy.manager.pojo.OrderShippingExample;

public interface OrderShippingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_shipping
     *
     * @mbggenerated
     */
    int countByExample(OrderShippingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_shipping
     *
     * @mbggenerated
     */
    int deleteByExample(OrderShippingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_shipping
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String orderId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_shipping
     *
     * @mbggenerated
     */
    int insert(OrderShipping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_shipping
     *
     * @mbggenerated
     */
    int insertSelective(OrderShipping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_shipping
     *
     * @mbggenerated
     */
    List<OrderShipping> selectByExample(OrderShippingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_shipping
     *
     * @mbggenerated
     */
    OrderShipping selectByPrimaryKey(String orderId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_shipping
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") OrderShipping record, @Param("example") OrderShippingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_shipping
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") OrderShipping record, @Param("example") OrderShippingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_shipping
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(OrderShipping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_shipping
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(OrderShipping record);
}
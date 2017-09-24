package com.weidian.dao;

import com.weidian.pojo.OrderItem;
import java.util.List;

public interface OrderItemMapper {


    int deleteByPrimaryKey(String orderItemid);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

  

    OrderItem selectByPrimaryKey(String orderItemid);



    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
    
    List<OrderItem> selectByOrderID(String orderid);
}
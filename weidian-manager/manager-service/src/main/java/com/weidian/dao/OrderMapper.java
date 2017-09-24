package com.weidian.dao;

import java.util.List;

import com.weidian.pojo.Item;
import com.weidian.pojo.Order;
import com.weidian.pojo.User;

public interface OrderMapper {
    int deleteByPrimaryKey(String orderid);

    int addOrder(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderid);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    List<Order> findAllOrder(String userid);
 
    User selectUserBalance(String orderid);
}
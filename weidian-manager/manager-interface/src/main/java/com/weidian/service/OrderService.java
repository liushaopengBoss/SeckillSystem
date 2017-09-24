package com.weidian.service;

import java.util.List;

import com.weidian.pojo.Item;
import com.weidian.pojo.Order;

public interface OrderService {

	/**
	 * 添加订单
	 * @param userid  用户ID 
	 * @param itemid  商品ID
	 * @return
	 */
	Item addOrder(String userid,Long itemid);
	/**
	 * 查询所有订单
	 * @param userid
	 * @return
	 */
	List<Order> findAllOrder(String userid);
	/**
	 *购买 
	 */
	int buyOne(String orderid,String userid);
	/**
	 * 实现多个商品生成一个订单
	 * @param userid
	 * @param itemid
	 * @return
	 */
	Order addOrders(String userid,Long[] itemid);
	/**
	 * 购买多个
	 * @param orderid
	 * @param userid
	 * @return
	 */
	int buyMany(String orderid,String userid);

}

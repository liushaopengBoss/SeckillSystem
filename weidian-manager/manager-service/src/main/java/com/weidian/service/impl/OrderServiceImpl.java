package com.weidian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weidian.dao.ItemMapper;
import com.weidian.dao.OrderItemMapper;
import com.weidian.dao.OrderMapper;
import com.weidian.dao.UserMapper;
import com.weidian.pojo.Item;
import com.weidian.pojo.Order;
import com.weidian.pojo.OrderItem;
import com.weidian.pojo.User;
import com.weidian.service.CartService;
import com.weidian.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderMapper orderMapper;
	@Autowired
	ItemMapper itemMapper;
	@Autowired
	OrderItemMapper orderItemMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	CartService cartService;

//通过itemID查商品信息   组装成order对象和订单条目   保存到数据库

	@Override
	public Item addOrder(String userid, Long itemid) {
		// TODO Auto-generated method stub
		//1.通过itemID查商品信息
		Item item = itemMapper.selectByID(itemid);
		//2.组装成order对象和订单条目
		Order order=new Order();
		order.setCreateTime(new Date());
		String orderid = UUID.randomUUID().toString();
		order.setOrderid(orderid);
		order.setPayment(item.getPrice());
		//未付款为0，已付款为1
		order.setStatu(0);
		order.setUserid(userid);
		
		OrderItem orderItem = new OrderItem();
		orderItem.setItemid(itemid);
		orderItem.setOrderid(orderid);
		item.setOrderID(orderid);
		long orderItemid = System.nanoTime();
		orderItem.setOrderItemid(orderItemid);
		orderItem.setQuantity(1);		
		//3.保存到数据库
		orderMapper.addOrder(order);
		orderItemMapper.insert(orderItem);
	
		return item;
	}


	@Override
	public List<Order> findAllOrder(String userid) {
		// TODO Auto-generated method stub
		//通过userid查orderid
		List<Order> list = orderMapper.findAllOrder(userid);
		for (Order order : list) {
			String orderid = order.getOrderid();
			//通过orderid查itemid
			List<OrderItem> itemids = orderItemMapper.selectByOrderID(orderid);
			List<Item> items = new ArrayList<Item>();
			for (OrderItem orderItem : itemids) {
				Long itemid = orderItem.getItemid();
				//通过 itemid查item信息
				Item item = itemMapper.selectByID(itemid);
				items.add(item);
			}
			order.setItemlist(items);
		}
		return list;
	}


	@Override
	public int buyOne(String orderid, String userid) {
		// TODO Auto-generated method stub
		//1.获取买家余额 卖家余额 
		User buyuser = userMapper.selectByPrimaryKey(userid);
		Integer buyuserbalance = buyuser.getBalance();
		
		User sailuser = orderMapper.selectUserBalance(orderid);
		Integer sailuserbalance = sailuser.getBalance();
		//2.获取商品ID减库存
		List<Item> items= new ArrayList<Item>();
		
		int totalprice=0;
		List<OrderItem> itemids = orderItemMapper.selectByOrderID(orderid);
		for (OrderItem orderItem : itemids) {
			Long itemid = orderItem.getItemid();
			//通过 itemid查item信息
			Item item = itemMapper.selectByID(itemid);
			items.add(item);
			totalprice+=item.getPrice();
		}
		if(buyuserbalance>=totalprice){
			buyuserbalance-=totalprice;
			sailuserbalance+=totalprice;
			for (Item item : items) {
				Integer quantity = item.getQuantity();
				item.setQuantity(quantity-1);
				itemMapper.updateByPrimaryKeySelective(item);
			}
			//3.更改余额
			
			buyuser.setBalance(buyuserbalance);
			sailuser.setBalance(sailuserbalance);
			userMapper.updateByPrimaryKeySelective(buyuser);
			userMapper.updateByPrimaryKeySelective(sailuser);
			//4.更改订单状态
			Order order = orderMapper.selectByPrimaryKey(orderid);
			order.setStatu(1);
			orderMapper.updateByPrimaryKeySelective(order);
			return 1;
		}else{
			return 0;
		}
		
	}


	@Override
	public Order addOrders(String userid, Long[] itemid) {
		// TODO Auto-generated method stub
		
		
		//2.组装成order和orderitem
		Order order = new Order();
		order.setCreateTime(new Date());
		String orderid = UUID.randomUUID().toString();
		order.setOrderid(orderid);
		order.setUserid(userid);
		//0未付款  1已付款
		order.setStatu(0);
		
		//1.通过itemid查询item对象
		List<Item> itemlist = new ArrayList<Item>();
		int totalprice = 0;
		for (int i = 0; i < itemid.length; i++) {
			Item item = itemMapper.selectByPrimaryKey(itemid[i]);
			totalprice+=item.getPrice();
			itemlist.add(item);
			OrderItem orderItem = new OrderItem();
			orderItem.setItemid(item.getItemid());
			orderItem.setOrderid(orderid);
			long orderItemid = System.nanoTime();
			orderItem.setOrderItemid(orderItemid);
			orderItem.setQuantity(1);
			orderItemMapper.insert(orderItem);
			//清除购物车
			cartService.delectCartByItemid(itemid[i], userid);
		}
		order.setItemlist(itemlist);
		order.setPayment(Long.parseLong(totalprice+""));
		//3.保存到数据库
		orderMapper.addOrder(order);
		
		
		
		return order;
	}

	@Override
	public int buyMany(String orderid, String userid) {
		// TODO Auto-generated method stub
		return 0;
	}
		
		
}


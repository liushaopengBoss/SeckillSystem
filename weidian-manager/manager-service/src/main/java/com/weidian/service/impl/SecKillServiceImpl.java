package com.weidian.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import com.google.gson.Gson;
import com.weidian.dao.ItemMapper;
import com.weidian.dao.OrderItemMapper;
import com.weidian.dao.OrderMapper;
import com.weidian.dao.UserMapper;
import com.weidian.pojo.Item;
import com.weidian.pojo.Order;
import com.weidian.pojo.OrderItem;
import com.weidian.pojo.User;
import com.weidian.service.SecKillService;

@Service
public class SecKillServiceImpl implements SecKillService {

	@Autowired
	JedisPool pool;

	@Autowired
	OrderItemMapper ordermapper;
	
	@Autowired
	OrderMapper ordersmapper;
	@Autowired
	UserMapper usermapper;
	@Autowired
	ItemMapper itemmapper;
	
	@Override
	public Long refreshTime(Long itemid) throws ParseException {
													//	YYYY-MM-DD HH:mm:ss  yyyy-MM-dd hh:mm:ss
//		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		Date date=sdf2.parse("2017-06-03 10:42:44");  
//		Long times = date.getTime();

		
		
		
		Jedis redis = pool.getResource();
		//redis.set("itemMS:"+itemid, times+"");
		//redis获取商品秒杀时间
		String startTimeStr = redis.get("itemMS:"+itemid);
		//剩余时间
		Long rt = null  ;
		if(startTimeStr!=null){
			//转换
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format = sdf.format(new Date());
			//现在时间
			Long nowTime = sdf.parse(format).getTime();
			
			//秒杀时间
			Long startTime = Long.parseLong(startTimeStr);
			//剩余时间
			rt = startTime-nowTime;
		}
		//关闭连接
		redis.close();
		
		return rt;
	}

	@Override
	public int findSurplusItemNum(Long itemid) {
		Jedis redis = pool.getResource();
		//获取秒杀商品的数量
		String num = redis.get("itemMSNo:"+itemid);
		//关闭连接
		redis.close();
		if(num!=null){
			return Integer.parseInt(num);
		}
		return -1;
	}

	@Override
	public Item findSurplusItemInfo(Long itemid) {
		Jedis redis = pool.getResource();
		String itemJson = redis.hget("ItemMSInfo", itemid+"");
		//关闭连接
		redis.close();
		//转化为item
		Gson gson = new Gson();
		Item item = gson.fromJson(itemJson, Item.class);
		return item;
	}

	@Override
	public boolean surplusItemPay(Long itemid, String userID) {
		Jedis redis = pool.getResource();
		String itemJson = redis.hget("ItemMSInfo", itemid+"");
		Gson gson = new Gson();
		Item item = gson.fromJson(itemJson, Item.class);
		User user = usermapper.selectByPrimaryKey(userID);
		if(user.getBalance()*100>item.getPrice()){
			//键加锁
			redis.watch("itemMSNo:"+itemid);
			//获取库存
			int num = Integer.parseInt(redis.get("itemMSNo:"+itemid))-1;
			//事务
			Transaction transaction = redis.multi();
			transaction.set("itemMSNo:"+itemid,  num+"");
			//执行
			List<Object> result = transaction.exec();
			item.setQuantity(num);
			String updateItem = gson.toJson(item);
			/*System.out.println("item----"+updateItem);*/
			redis.hset("ItemMSInfo", itemid+"", updateItem);
			redis.close();
			
			if (result == null || result.isEmpty()) {
	            //秒杀失败了
				 System.out.println("Transaction error...");// 可能是watch-key被外部修改，或者是数据操作被驳回
				 return false;
	        }else{	
	        	//插入mysql数据库
	        	String uid = UUID.randomUUID().toString();
	        	Order order = new Order();
	        	order.setCreateTime(new Date());
	        	order.setOrderid(uid);
	        	order.setPayment(item.getPrice());
	        	order.setStatu(1);
	        	order.setUserid(userID);
	        	
	        	OrderItem orderitem = new OrderItem();
	        	orderitem.setItemid(itemid);
	        	
	        	orderitem.setOrderid(uid);
	        	orderitem.setOrderItemid(System.nanoTime());
	        	orderitem.setQuantity(1);
	        	
	        	ordersmapper.addOrder(order);
	        	ordermapper.insert(orderitem);
	        	long balance = user.getBalance()-item.getPrice();
	        	user.setBalance(Integer.parseInt(balance+""));
	        	
	        	itemmapper.updateByPrimaryKey(item);
	        	usermapper.updateByPrimaryKey(user);
	        	
	        	return true;
	        }
		
		}else{
			return false;
		}
		
		
	}


}

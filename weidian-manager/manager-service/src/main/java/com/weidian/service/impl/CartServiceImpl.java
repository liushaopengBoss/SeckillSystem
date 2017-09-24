package com.weidian.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.google.gson.Gson;
import com.weidian.dao.CartMapper;
import com.weidian.dao.ItemMapper;
import com.weidian.pojo.Cart;
import com.weidian.pojo.CartExample;
import com.weidian.pojo.Item;
import com.weidian.service.CartService;


@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	CartMapper cartMapper;
	
	@Autowired
	ItemMapper itemMapper;
	
	@Autowired
	JedisPool pool;

	@Override
	public int countByExample(CartExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByExample(CartExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	//购物车添加商品信息
	public int insert(Cart record) {
		
		return cartMapper.insert(record);
		
	}

	@Override
	public int insertSelective(Cart record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Cart> selectByExample(CartExample example) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public int updateByExampleSelective(Cart record, CartExample example) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int updateByExample(Cart record, CartExample example) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	@Override
	public List<Cart> selectAll() {
		// TODO Auto-generated method stub
		return cartMapper.selectCartAll();
	}

	@Override
	public String deleteByItemId(Long itemid) {
		// TODO Auto-generated method stub
		return cartMapper.deleteByItemId(itemid);
	}

	@Override
	public String findCartByItemID(Long itemid) {
		// TODO Auto-generated method stub
		return cartMapper.findCartByItemID(itemid);
	}

	//1.从item表中查出item信息  2.把item信息转化为cart 3.插入cart对象 4.把购物车加入缓存
	@Override
	public void insertItemtoCart(Long itemID,String userid) {
		// TODO Auto-generated method stub
		
		Item item = itemMapper.selectByPrimaryKey(itemID);
		Cart cart = new Cart();
		cart.setItemid(item.getItemid());
		cart.setImage(item.getImage());
		cart.setItemName(item.getItemName());
		cart.setPostage(item.getPostage());
		cart.setPrice(item.getPrice());
		cart.setQuantity(item.getQuantity());
		cart.setUserid(userid);
		cartMapper.insertItemtoCart(cart);
		Gson gson = new Gson();
		String json = gson.toJson(cart);
		try {
			Jedis jedis = pool.getResource();
			jedis.hset("cartInfo:"+userid, ""+itemID, json);
			jedis.close();
		} catch (Exception e) {
			
		}
		
	}

	@Override
	public List<Cart> selectCartByUserID(String userid) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		List<Cart> selectCartByUserID = new ArrayList<Cart>();
		try {
			Jedis jsdis = pool.getResource();
			List<String> hvals = jsdis.hvals("cartInfo:"+userid);
			for(int i=0;i<hvals.size();i++){
				String string = hvals.get(i);
				//通过gson把缓存中取出的对象json串转化为Cart对象
				Cart fromJson = gson.fromJson(string, Cart.class);
				selectCartByUserID.add(fromJson);
			}
			jsdis.close();
		} catch (Exception e) {
			selectCartByUserID = cartMapper.selectCartByUserID(userid);
		}
		return selectCartByUserID;
	}

	@Override
	public void delectCartByItemid(Long itemid,String userid) {
		try {
			Jedis jedis = pool.getResource();
			jedis.hdel("cartInfo:"+userid,itemid+"");
			jedis.close();
		} catch (Exception e) {
			
		}
		
		//
		cartMapper.deleteCartByItemid(itemid,userid);
	}

	/**
	 * 计算总价格
	 * @return
	 */
	public double totalMoney(String userid){

		//初始化总价
		long totalPrice = 0 ;
		//查询总记录数
		List<Cart> cartAllList = cartMapper.selectCartByUserID(userid);
		int total = cartAllList.size();
		for (int i = 0; i < total; i++) {
			totalPrice = (cartAllList.get(i).getPrice()*cartAllList.get(i).getQuantity()+cartAllList.get(i).getPostage());
			System.out.println("--------总价是totalPrice"+totalPrice);
		}

		return (double)totalPrice/100;
	}

	

}

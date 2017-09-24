package com.weidian.service;

import java.util.List;

import com.weidian.pojo.Cart;
import com.weidian.pojo.CartExample;


public interface CartService {

	int countByExample(CartExample example);

	int deleteByExample(CartExample example);

	//删除商品
	String deleteByItemId(Long itemid);
	
	int insert(Cart record);

	int insertSelective(Cart record);

	List<Cart> selectByExample(CartExample example);
	
	//删除购物车中商品
    void delectCartByItemid(Long itemid,String userid);

    double totalMoney(String userid);

	List<Cart> selectAll();
	
	//根据findCartByItemID 查询购物车信息
    String findCartByItemID(Long itemid);

    //添加购物车
	void insertItemtoCart(Long itemID,String userid);
    
	//根据用户ID查询购物车信息
	List<Cart> selectCartByUserID(String userid);
}

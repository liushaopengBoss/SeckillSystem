package com.weidian.service;


import com.weidian.pojo.Shop;

public interface ShopService {
	
	Shop selectByShopId(String userid);
	//注册一个商户
	int insertShop(Shop shop);
	
	
}

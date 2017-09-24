package com.weidian.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weidian.dao.ShopMapper;
import com.weidian.pojo.Shop;
import com.weidian.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService{

	@Autowired
	ShopMapper shopMapper;

	@Override
	public Shop selectByShopId(String userid) {
		// TODO Auto-generated method stub
		return shopMapper.selectByUserId(userid);
	}

	@Override
	public int insertShop(Shop shop) {
		int i=shopMapper.insert(shop);
		return i;
	}
	
}

package com.weidian.service.impl;


import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.google.gson.Gson;
import com.weidian.dao.ItemMapper;
import com.weidian.pojo.Item;
import com.weidian.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired ItemMapper itemmapper;
	@Autowired JedisPool pool;
	@Override
	public List<Item> selectByLimit(int startpage, int pagesize) {
		

		
		Jedis redis = pool.getResource();

		List<String> hvals = redis.hvals("ItemMSInfo");
		redis.close();
		List<Item> selectItemsByLimit = new ArrayList<>();;
		
		if(startpage==0){
			if(hvals.size()<30){
				Gson g = new Gson();
				for (int i = 0; i < hvals.size(); i++) {
					selectItemsByLimit.add(g.fromJson(hvals.get(i), Item.class));
				}
				List<Item> items = itemmapper.selectByLimit(startpage*30, 30-hvals.size());
				selectItemsByLimit.addAll(items);
				return selectItemsByLimit;
			}else{
				Gson g = new Gson();
				for (int i = 0; i < hvals.size(); i++) {
					selectItemsByLimit.add(g.fromJson(hvals.get(i), Item.class));
				}
				
				return selectItemsByLimit;
			}
		}else{
			selectItemsByLimit = itemmapper.selectByLimit((startpage-1)*30, 30);
		}
		
		return selectItemsByLimit;
		

	}
	@Override
	public int insert(Item record) {
			try {
				if(record.getSeckill()!=null){
					Gson gson=new Gson();
					  Jedis redis = pool.getResource();
					  String itemjson=gson.toJson(record);
					  redis.hset("ItemMSInfo",record.getItemid().toString(),itemjson);
					  redis.set("itemMS:"+record.getItemid(),record.getSeckill().getTime()+"");
					  redis.set("itemMSNo:"+record.getItemid(),record.getQuantity().toString());
					  redis.close();
				}
				 
				itemmapper.insert(record);
			} catch (Exception e) {
			
				e.printStackTrace();
			}  

			

		return 0;
	}
	
	@Override
	public int selectTotal() {
		// TODO Auto-generated method stub
		Jedis redis = pool.getResource();
		String num = redis.get("itemNumber");
		if(num==null){
			//
			int selectTotal = itemmapper.selectTotal();
			
			redis.set("itemNumber", selectTotal+"");
			redis.close();
			return selectTotal;
		}else{
			redis.close();
			return Integer.parseInt(num);
		}
		
	}

	@Override
	public Item selectByID(Long itemid) {
		// TODO Auto-generated method stub
		Item selectByID = itemmapper.selectByID(itemid);
		return selectByID;
	}

	@Override
	public Item findItemByItemID(Long itemid) {
		
		return itemmapper.findItemByItemID(itemid);
	}



	@Override
	public int updateQuanityByItemid(Long itemid) {
		// TODO Auto-generated method stub
		return itemmapper.updateQuanityByItemid(itemid);
	}

	@Override
	public List<Item> selectQuantityByItemId(Long itemid) {
		// TODO Auto-generated method stub
		return itemmapper.selectQuantityByItemId(itemid);
	}


	@Override
	public List<Item> selectByShopId(Integer shopId) {
		List<Item> itemlist=itemmapper.selectByShopId(shopId);		
		return itemlist;
	}

	@Override
	public String selectByShopIdandItemId(Long Itemid, Integer shopId) {
		//String shopid_itemid=shopId.toString()+"_"+Itemid.toString();
		Jedis redis=pool.getResource();
		String item;
		if(redis.hexists("ItemMSInfo",Itemid.toString())){
			item= redis.hget("ItemMSInfo",Itemid.toString());			
		}
		Item i=itemmapper.findItemByItemID(Itemid);
		item=i.toString();
		return item;
	}

}

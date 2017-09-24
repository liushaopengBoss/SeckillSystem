package com.weidian.service;

import java.util.List;

import com.weidian.pojo.Item;

public interface ItemService {

	List<Item> selectByLimit(int startpage,int pagesize);
	int selectTotal();
	//通过商品ID商品详情
	Item selectByID(Long itemid);
	
	public int insert(Item record);
    //
    Item findItemByItemID(Long itemid);
    
    
    /**
     * 从item 表中向 cart中每增加一条对应的商品
     * 库存量qnantity 就减少一
     */
    int updateQuanityByItemid(Long itemid);
    
    /**
     * 向item表中根据itemid查询商品数量，如果数量大于 1 才可以执行操作
     * @param itemid
     * @return
     */
    List<Item> selectQuantityByItemId(Long itemid);
    //店铺
    List<Item> selectByShopId(Integer shopId);
    
    String selectByShopIdandItemId(Long Itemid,Integer shopId);
}

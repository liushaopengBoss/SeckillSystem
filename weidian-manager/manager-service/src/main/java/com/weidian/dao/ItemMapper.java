package com.weidian.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.weidian.pojo.Item;

public interface ItemMapper {
    int deleteByPrimaryKey(Long itemid);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Long itemid);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKeyWithBLOBs(Item record);

    int updateByPrimaryKey(Item record);
    //分页查询
    List<Item> selectByLimit(@Param("startpage")int startpage,@Param("pagesize")int pagesize);
    //查询总条数
    int selectTotal();
    //通过商品Id获取商品数据
    Item selectByID(Long itemid);
    
    /**
     * 根据findItemByItemID 查找商品id
     * String类型
     */
    Item findItemByItemID(@Param("itemid")Long itemid);
   
    /**
     * 从item 表中向 cart中每增加一条对应的商品
     * 库存量qnantity 就减少一
     */
    int updateQuanityByItemid(@Param("itemid")Long itemid);
    
    /**
     * 向item表中根据itemid查询商品数量，如果数量大于 1 才可以执行操作
     * @param itemid
     * @return
     */
    List<Item> selectQuantityByItemId(@Param("itemid")Long itemid);
    
    
    List<Item> selectByShopId(@Param("shopid")Integer shopid);
    
    Item selectByshopIdAndItemId(@Param("itemid")Long itemid,@Param("shopid")Integer shopid);
}
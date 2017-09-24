package com.weidian.dao;

import org.apache.ibatis.annotations.Param;

import com.weidian.pojo.Shop;

public interface ShopMapper {
    int deleteByPrimaryKey(Integer shopid);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(Integer shopid);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);
    

    //根据用户名查询商店
    Shop selectByUserId(@Param(value="userid")String userid);
}
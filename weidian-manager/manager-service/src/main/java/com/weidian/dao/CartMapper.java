package com.weidian.dao;

import com.weidian.pojo.Cart;
import com.weidian.pojo.CartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CartMapper {
	
    int countByExample(CartExample example);

    int deleteByExample(CartExample example);

    int insert(Cart record);

    int insertSelective(Cart record);

    List<Cart> selectByExample(CartExample example);

    int updateByExampleSelective(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByExample(@Param("record") Cart record, @Param("example") CartExample example);
    
    //查询购物车所有信息
    List<Cart> selectCartAll();
    
    //根据商品id删除信息
    String deleteByItemId(Long itemid);
    
    //根据findCartByItemID 查询购物车信息
    String findCartByItemID(Long itemid);
    
    //添加商品到购物车
    void insertItemtoCart(Cart cart);
    
    List<Cart> selectCartByUserID(String userid);
    
    //根据商品id删除信息删除购物车中商品
    void deleteCartByItemid(@Param("itemid")Long itemid,@Param("userid")String userid);

    
}
package com.weidian.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.weidian.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(String userid);
    //注册
    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User login(String username);
    //用户名查重
    User checkUsername(String username);
    /**
     * 计算账户余额
     */
    int updateUserBalanceCount(@Param("balance")double balance,@Param("userid")String userid);
    
    /**
     * 查询当前用户账户余额
     */
    List<User> selectBallanceAccount(@Param("userid")String userid);
}
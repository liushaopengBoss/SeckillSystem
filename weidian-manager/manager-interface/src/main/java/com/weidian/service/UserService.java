package com.weidian.service;

import com.weidian.pojo.Shop;
import com.weidian.pojo.User;

public interface UserService {

	 	int deleteByPrimaryKey(Long id);

	    int insert(User record);

	    int insertSelective(User record);

	    User selectByPrimaryKey(Long id);

	    int updateByPrimaryKeySelective(User record);

	    int updateByPrimaryKey(User record);
	    
	    //登录
	    User login(User user);
	    
	    //注册
	    void regist(User user,Shop shop);
	    
	    //用户名查重
	    boolean checkUsername(String username);

}

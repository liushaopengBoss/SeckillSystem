package com.weidian.service.impl;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPool;

import com.weidian.dao.ItemMapper;

import com.weidian.dao.ShopMapper;
import com.weidian.dao.UserMapper;

import com.weidian.pojo.Shop;
import com.weidian.pojo.User;
import com.weidian.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	ShopMapper shopmapper;
	
	@Autowired
	UserMapper usermapper;
	
	@Autowired
	ItemMapper itemmapper;
	

	
	@Autowired
	JedisPool pool;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(User record) {
		
	
		return 0;
	}

	@Override
	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User selectByPrimaryKey(Long id) {

		return usermapper.selectByPrimaryKey(id+"");
		
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		User login = usermapper.login(user.getUsername());
		
		return login;
	}

	@Override
	public void regist(User user, Shop shop) {
		// TODO Auto-generated method stub
		if (shop!=null) {
			shopmapper.insert(shop);
		}
		usermapper.insert(user);
		
	}

	@Override
	public boolean checkUsername(String username) {
		// TODO Auto-generated method stub
		User checkUsername = usermapper.checkUsername(username);
		if(checkUsername==null){
			return false;
		}
		return true;
	}


}

package com.weidian.portal.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weidian.pojo.Item;
import com.weidian.pojo.Shop;
import com.weidian.pojo.User;
import com.weidian.service.ItemService;
import com.weidian.service.ShopService;

@Controller
@RequestMapping(value="/shop")
public class ShopController {
	@Autowired 
	ShopService shopService;
	@Autowired 
	ItemService itemService;
		
	@RequestMapping("SelectAndUserId")
	public String selectByUserId(HttpSession session,Model model){
		User u=(User) session.getAttribute("user");
		String userId=u.getUserid();
		Shop shop=shopService.selectByShopId(userId);
		Integer ShopId=shop.getShopid();
		List<Item> itemlist=itemService.selectByShopId(ShopId);
		model.addAttribute("shop", shop);
		model.addAttribute("itemlist", itemlist);
		return "shop";
	}
}

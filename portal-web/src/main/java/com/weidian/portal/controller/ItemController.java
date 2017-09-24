package com.weidian.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.weidian.pojo.Item;
import com.weidian.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	ItemService itemservice;
	
	@RequestMapping("/index")
	public String Limit(@RequestParam(value="page",defaultValue="1")int page , Model model){
		List<Item> selectByLimit = itemservice.selectByLimit((page-1)*30, 30);
		int selectTotal = itemservice.selectTotal();
		//把条目信息存入model域中    前台用${items}取出数据
		model.addAttribute("items",selectByLimit);
		model.addAttribute("totalPages",selectTotal/30+(selectTotal % 30==0?0:1));
		model.addAttribute("startPage",page);
		
		return "index";
		
	}
	
	@RequestMapping("details")
	public String SelectByID(Long itemid,Model model){
		Item selectByID = itemservice.selectByID(itemid);
		model.addAttribute("item",selectByID);
		return "detail";
	}
	
}

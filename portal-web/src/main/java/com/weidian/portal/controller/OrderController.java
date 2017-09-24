package com.weidian.portal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weidian.pojo.Item;
import com.weidian.pojo.Order;
import com.weidian.pojo.User;
import com.weidian.service.OrderService;


@Controller
@RequestMapping(value="/order")
public class OrderController {
	
	@Autowired 
	OrderService orderService;
	
	@RequestMapping(value="/orderadd")
	public String addOrder(Long itemid,HttpSession session,Model model){	
		User user = (User)session.getAttribute("user");
		String userid = user.getUserid();
		Item item = orderService.addOrder(userid, itemid);
		List<Item> list= new ArrayList<Item>();
		list.add(item);
		model.addAttribute("items", list);
		return "order";
	}
	
	@RequestMapping(value="/allOrder")
	public String selectAllOrder(Model model,HttpSession session){
		User user = (User) session.getAttribute("user");
		String userid = user.getUserid();
		List<Order> orders = orderService.findAllOrder(userid);
		model.addAttribute("orders", orders);
		return"orders";
	}
	
	@RequestMapping(value="/buyone")
	public String buyOne(String orderid,HttpSession session, Model model){
		User user = (User)session.getAttribute("user");
		String userid = user.getUserid();
		int buyOne = orderService.buyOne(orderid, userid);
		if(buyOne==0){
			model.addAttribute("message","余额不足！");
		}else{
			model.addAttribute("message","购买成功！");
		}
		
		return "success";
		
	}
	
	@RequestMapping(value="/orderTeg")
	public String addOrders(HttpSession session,Long[] itemid,Model model){
		User user = (User)session.getAttribute("user");
		String userid = user.getUserid();
		Order orders = orderService.addOrders(userid, itemid);
		model.addAttribute("orders",orders);
		return "orderTeg";
	}
}

package com.weidian.portal.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weidian.pojo.Cart;
import com.weidian.pojo.User;
import com.weidian.service.CartService;

@Controller
@RequestMapping(value="/cart")
public class CartController {
	
	@Resource
	CartService cartservice;
	
	//将商品加入购物车
	@RequestMapping("/insertcart")
	@ResponseBody
	public String insertItemtoCart(Long itemID,HttpSession session){
		//1.从session中取出user  2.从user中取出userID  3.从service中获取方法并传参
		try {
			User user = (User) session.getAttribute("user");
			String userid = user.getUserid();
			cartservice.insertItemtoCart(itemID, userid);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return "false";
		}
		
		return "true";
	}
	@RequestMapping("/carts")
	public String selectCartByUserID(HttpSession session,Model model){
		User user = (User) session.getAttribute("user");
		String userid = user.getUserid();
		List<Cart> selectCartByUserID = cartservice.selectCartByUserID(userid);
		model.addAttribute("carts",selectCartByUserID );
		return "cart";
		
	}
	@RequestMapping("/deletecart")
	public String deleteCartByItemid(HttpSession session,Long itemid){
		User user = (User) session.getAttribute("user");
		String userid = user.getUserid();
		cartservice.delectCartByItemid(itemid,userid);
		return "redirect:/cart/carts.html";
	}
}

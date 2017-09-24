package com.weidian.portal.controller;



import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.weidian.pojo.Item;
import com.weidian.pojo.Shop;
import com.weidian.pojo.User;
import com.weidian.service.ItemService;
import com.weidian.service.ShopService;
import com.weidian.utils.IDUtils;
import com.weidian.utils.JsonUtils;


@Controller
@RequestMapping(value="/item")

public class GoodController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	ItemService itemService;
	@Autowired
	ShopService shopService;
	/*
	 * 添加商品
	 */	
		@RequestMapping("/AddItem")
	public String Add(@RequestParam("itemName") String itemName,
			@RequestParam("quantity") Integer quantity,
			@RequestParam("price") long price,
			@RequestParam("postage") long postage,
			@RequestParam(value="seckill",required=false) String seckill,
			@RequestParam(value="comments",required=false) String comments,
			@RequestParam(value="shopId") Integer shopId,
            HttpServletRequest request,
            HttpServletResponse response){	
			
			User user = (User) request.getSession().getAttribute("user");
			Shop shop = shopService.selectByShopId(user.getUserid());
			
			
			// 从请求中获取到文件信息需要将请求转换为MultipartHttpServletRequest类型  
			MultipartHttpServletRequest MulRequest = request instanceof MultipartHttpServletRequest ? (MultipartHttpServletRequest) request : null;  
			 Iterator<String> fileNames = MulRequest.getFileNames();
			  MultipartFile file=null;
		      String newFileName1;
		      String Fileaddress = null;
		      String[] photo =new String[2];
		      int i=0;
		      while (fileNames.hasNext()) { // 遍历请求中的图片信息  
		          String fileName = fileNames.next(); // 图片对应的参数名  
		          file =  MulRequest.getFile(fileName); // 获取到图片  
		          
		          if (file != null && !((MultipartFile) file).isEmpty()) {
		              // 获取图片的文件名
		              String fileName1 = file.getOriginalFilename();
		              // 获取图片的扩展名
		              String extensionName = fileName1
		                      .substring(fileName1.lastIndexOf(".") + 1);
		              // 新的图片文件名 = 获取时间戳+"."图片扩展名
		             // System.currentTimeMillis() 获得的是自1970-1-01 00:00:00.000 到当前时刻的时间距离,
		               newFileName1 = String.valueOf(System.currentTimeMillis())
		                      + "." + extensionName;
		              try {
		            	  Fileaddress=saveFile(newFileName1, file);
		              } catch (Exception e) {
		              	e.printStackTrace();
		              }
		              
		              photo[i]=Fileaddress+newFileName1;
		              //po+=photo[i];
		              i++;
		          }
		      }
		      String Photo=photo[0]+","+photo[1];
			   Item item=new Item();
			   long itemid=IDUtils.genItemId();
			   item.setItemid(itemid);
			   item.setItemName(itemName);
			   item.setQuantity(quantity);
			   item.setPrice(price*100);
			   item.setPostage(postage*100);
			   item.setCreated(new Date());
			   item.setShopID(shop.getShopid());
			   item.setShopid(shop.getShopid());
			   item.setShopName(shop.getShopName());
			   item.setComments(comments);
			   item.setImage(Photo);

			   /**
			    * 	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				   Date date=sdf.parse(record.getMsStr());  
					Long times = date.getTime();
					System.out.println(record.getMsStr());
				   System.out.println("dsfdrf--->"+times);
				   
					String format = sdf.format(new Date());
					//现在时间
					Long nowTime = sdf.parse(format).getTime();

					System.out.println("dangqin"+nowTime);
				   System.out.println(times-nowTime);
			    */
			   
		      if(seckill!=null && !seckill.equals("")){    	 
			      String format="yyyy-MM-dd HH:mm:ss";
			      SimpleDateFormat sdf=new SimpleDateFormat(format);
			      Date date=null;
			      try {
					date=sdf.parse(seckill);
				} catch (ParseException e) {
					System.out.println("转化失败");
				}
			      item.setSeckill(date);
		    }
		 
		   itemService.insert(item);
		//System.out.println("photo1"+photo1);
		return "redirect:/shop/SelectAndUserId.html";
	}
		
	
		//根据商户Id和商品Id	
	@RequestMapping("SelectByShopAndItemId")		
	public String selectByShopAndItemId(@RequestParam(value="itemId") long itemId,@RequestParam(value="shopId") Integer shopId,Model model){
		String item=itemService.selectByShopIdandItemId(itemId, shopId);
		Item item1=JsonUtils.jsonToPojo(item, Item.class);
		model.addAttribute(item1);
		return null;
	}
	
	
	//根据商户Id查询商品
	@RequestMapping("SelectByShop")
		public String SelectByShopId(@RequestParam(value="shopId") Integer shopId,HttpSession session){
		List<Item> itemlist=itemService.selectByShopId(shopId);
		System.out.println(itemlist);
		return null;
	}
			
		
	 /**
	       * 
	       * 功能描述   保存图片
	       * @param newFileName
	       *            上传照片文件名
	       * @param filedata
	       *           文件数据
	       */
	      private String saveFile(String newFileName, MultipartFile filedata) {
	          // TODO Auto-generated method stub
	          // 根据配置文件获取服务器图片存放路径
	          String picDir = request.getSession().getServletContext().getRealPath("/")+"upload/";
	          String saveFilePath = picDir;
	          /* 构建文件目录 */
	          File fileDir = new File(saveFilePath);
	          if (!fileDir.exists()) {
	              fileDir.mkdirs();
	          }
	          try {
	              FileOutputStream out = new FileOutputStream(saveFilePath + "\\"
	                      + newFileName);
	              // 写入文件
	              out.write(filedata.getBytes());
	              out.flush();
	              out.close();
	          } catch (Exception e) {
	              e.printStackTrace();
	          }
     return picDir;
	      }
	     
	@RequestMapping("insertto")
	public String insertto(){
		return "publish";
	}
}

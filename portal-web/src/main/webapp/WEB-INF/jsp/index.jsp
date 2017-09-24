<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>微店</title>
</head>
<link href="/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<style type="text/css">
a{text-decoration:none}
	.header{
		width: 725px;
			height: 60px;
			border: 1px solid black;
			margin: 0 auto;
			background:#C8C8C8;
	}
	.header .title{
	position:absolute;
	top: 0px;
	}
	
	.header .rightlable{
		display:inline;
		float:right;
	}
	.header .rightlable div{
		font-size:20px;
		top:0px;
		display:inline;
	}
	
    .out{
        width: 725px;
        height: 5870px;
        border: 1px solid black;
        margin: 0 auto;

    }
    .out .list{
        width: 700px;
        height: 190px;
        border: 1px solid;
        margin: 5px 10px 2px 10px;
    }
    .imgs{
        width: 170px;
        height: 170px;
        margin: 10px 0px 10px 10px;
        background-color: greenyellow;
        float: left;
    }
    .out .leftlab{
        width: 300px;
        height: 170px;
        margin: 10px 0px 10px 10px;
        float: left;
        /*border: 1px solid;*/
        position: relative;
    }
    .out .leftlab div{
        width: 300px;
        height: 40px;
        margin: 5px 0px 0px 10px;
    }
    .out .rightlab{
        float: right;
        width: 180px;
        height: 170px;
        margin: 10px 0px 10px 10px;
    }
    .out .rightlab div{
        width: 180px;
        height: 40px;
        margin: 5px 0px 0px 10px;
    }
     .rightlab a{
        text-decoration: none;
    }
.leftlab button{
    position: absolute;
    width: 60px;
    height: 30px;
    line-height: 30px;
    text-align: center;
    top: 0px;
    right: 90px;
    background-color: deepskyblue;
    border: none;
    border-radius: 10px;
    color: white;

}
</style>
<body>
<!--外层div-->
<div class="header">
	<h1 class="title">电商秒杀系统<h1>
		
	    <div class="rightlable">
	    <c:if test="${empty sessionScope.user }">
			<!-- 未登录-->
			<div><a href="/login.html">登录</a></div>
			<div><a href="/regist.html">注册</a>&nbsp;&nbsp;&nbsp;&nbsp;</div>
		</c:if>
		 <c:if test="${not empty sessionScope.user }">
           <!-- 登录-->
		   <div><a href="/shop/SelectAndUserId.html">我的店铺</a></div>
			<div><a href="/cart/carts.html">购物车</a></div>
			<div><a href="/order/allOrder.html">我的订单</a></div>
			<div><a href="#">欢迎${sessionScope.user.username }登录</a></div>
			<div><a href="/user/logout.html">退出</a>&nbsp;&nbsp;&nbsp;&nbsp;</div>
		  </c:if>
        </div>  
</div>

<div class="out">
    <!--如果你是jsp页面的话直接通过foreach循环输出即可-->
    <!--foreach(){-->
    <c:forEach items="${items}" var="item">
    <div class="list">
        <!--左边-->
        <div class="left">
            <!--图片-->
            <div class="imgs">
                <a href="/details.html?itemid=${item.itemid }" >
                	<img src="https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i4/1258106044788429247/TB2OoeaqbFlpuFjy0FgXXbRBVXa_!!0-saturn_solar.jpg_180x180.jpg_.webp" style="width: 170px;height: 170px;" alt="">
                </a>
            </div>
            <!--左侧列表-->
            <div class="leftlab">
                <div style="display: inline-block">
                <a href="/details.html?itemid=${item.itemid }" > ${item.itemName }</a>
               </div>
                <div>价格:${item.price/100 }</div>
                <div>邮费:${item.postage/100 }</div>
                <div>剩余:${item.quantity }件</div>
            </div>
   
        </div>
        <!--右边-->
        <div class="rightlab">
            <div><a href="#">进入${item.shopName }店铺</a></div>

			<div>&nbsp;</div>
            <%-- 判断是否是秒杀商品 --%>
            <c:if test="${not  empty item.seckill }">
              <div style="margin-right: 130px;">离开始还剩 <span id="${item.itemid }">刷新</span></div>
            	<div><input style="color: red;width: 80px;border-radius: 5%;height: 30px;" onclick="refreshTime('${item.itemid }')" type="button" value="刷新" class="ms${item.itemid }"></input></div>
             </c:if>
              <c:if test="${empty item.seckill }">
              		<div>&nbsp;</div>
            		<div><input type="button" style="width: 80px;margin-left: 0px;border-radius: 5%;height: 30px;" onclick="addCart('${item.itemid }')" value="加购物车" /></div>
             </c:if>
        </div>
    </div>
    </c:forEach>
    
	<%-- 
	  <div class="list">
        <!--左边-->
        <div class="left">
            <!--图片-->
            <div class="imgs">
                <img src="image/imgs.jpg" style="width: 170px;height: 170px;" alt="">
            </div>
            <!--左侧列表-->
            <div class="leftlab">
                <div style="display: inline-block">这里是商品标题</div><button >秒杀</button>
                <div>价格:100.00</div>
                <div>邮费:10.00</div>
                <div>剩余:3 件</div>
            </div>

        </div>
        <!--右边-->
        <div class="rightlab">
            <div><a href="#">进入店铺</a></div>

            <div>&nbsp;</div>
            <div style="margin-right: 130px;">离开始还剩 <span>30:00</span></div>
            <div><button style="color: red;width: 80px;border-radius: 5%;height: 30px;">刷新</button></div>
        </div>
    </div>
	
--%>
    <!--foreach 结束-->
    
<%--分页 --%>
	 <input type="hidden" id="totalPages" value="${totalPages }"/> 
	 <input type="hidden" id="startPage" value="${startPage }"/> 
 <div class="pageLImit" > <ul id="pageLimit"></ul> </div>
 
</div>
		
</body>
<script src="js/jquery.min.js"></script>
      <script src="/js/bootstrap.min.js"></script>
	<script src="/js/bootstrap-paginator.min.js"></script>
	<script type="text/javascript">
	var time1;
	 
	function qg(itemID){
		window.location.href="/secKill/purchase.html?itemid="+itemID
	}
	
	
	function fresh(leftTime,itemID){

	    var d = parseInt(time1/(60*60*24));
	    var h = parseInt(time1/(60*60)%24);
	    var m = parseInt(time1/60%60);
	    var s = parseInt(time1%60);

	    if(time1<=0){ 
	    	
		    $("#"+itemID).html("开始抢购");
		    $(".ms"+itemID).attr("value","点击抢购");
	    	$(".ms"+itemID).attr("onclick","qg('"+itemID+"')");
	    }else{
	    	var txt = "："+d+"天"+h+"小时"+m+"分钟"+s+".秒";
		    $("#"+itemID).html(txt);
	    }
	    time1 = time1-1;
	 
	}
	function setIntervals(time,itemID){
		time1 = time/1000;
		var set = setInterval("fresh("+(time)+",'"+itemID+"')",1000);
		
	}
	
	$(function(){

		//添加购物车
		addCart = function (itemID){
			
			$.get("/cart/insertcart.html",{"itemID":itemID},function(data){
				if(data=="true"){
					alert("购物车添加成功")
					
				}else{
					alert("购物车添加失败")
				}
			});
			
		}
		//刷新时间
		refreshTime = function(itemID){
			 $.post("/secKill/time.html",{"itemid":itemID},function(time){
				
				 setIntervals(time,itemID);
				
			});
		}
		

	});


	
	
	
	</script>
	<script type="text/javascript">
	$(function(){
				//分页
					var options = { 
							bootstrapMajorVersion:3, //版本
	                      currentPage:$("#startPage").val(), //当前页数
	                      totalPages:$("#totalPages").val(), //总页数
	                      size:"normal",//设置控件的显示大小，是个字符串. 允许的值: mini, small, normal,large。值：mini版的、小号的、正常的、大号的。
	                      numberOfPages:5, //总页数,//设置分页每次显示的页数
	                      shouldShowPage:true, // 该参数用于设置某个操作按钮是否显示
	                      itemTexts: function (type, page, current) {
	                          switch (type) {
	                              case "first":
	                                  return "首页";
	                              case "prev":
	                                  return "上一页";
	                              case "next":
	                                  return "下一页";
	                              case "last":
	                                  return "末页";
	                              case "page":
	                                  return page;
	                          }
	                      },//点击事件，用于通过Ajax来刷新整个list列表
	                      onPageClicked: function (event, originalEvent, type, page){
	                 			window.location.href="/index.html?page="+page;
	                    	  
	                      }
	                  };
					
					  $('#pageLimit').bootstrapPaginator(options);
					
	});

    
	
	</script>
</html>
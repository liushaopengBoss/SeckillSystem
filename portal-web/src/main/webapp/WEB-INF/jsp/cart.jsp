<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
  <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>微店</title>
</head>
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
	
	.shop{
			width: 725px;
			height: 60px;
			border: 1px solid black;
			margin: 0 auto;

	}
	.shop .shopname{
	position:absolute;
	top: 70px;
	}
	.shop .button{
	float:right;
	}
	
    .out{
        width: 725px;
        height: 600px;
       
        margin: 0 auto;

    }
    .out .list{
        width: 725px;
        height: 210px;
        border: 1px solid;
        margin: 5px 0px 2px 0px;
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
.footer{
	width: 725px;
			height: 60px;

}
.footer button{
		 margin: 10px 0px 0px 10px;
		float:right;
	
}
</style>
<body>
<div class="header">
	<h1 class="title">电商秒杀系统<h1>
		
	    <div class="rightlable">
           <!-- 登录-->
		   <div><a href="/">我的店铺</a></div>
			<div><a href="/order/allOrder.html">我的订单</a></div>
			<div><a href="#">欢迎${sessionScope.user.username }登录</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
        </div>
</div >

<div class="shop">
	<h3 class="shopname">购物车<h3>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="checkbox" id="AllcheckBox" checked="checked">全选</input>
</div>

<!--外层div-->

<div class="out">
<form action="/order/orderTeg.html" method="post">
    <!--如果你是jsp页面的话直接通过foreach循环输出即可-->
    <!--foreach(){-->
    <c:forEach items="${carts }" var = "cart">
    <div class="list">
        <!--左边-->
		<input type="checkbox" name="itemid" value="${cart.itemid }" checked="checked"/>
        <div class="left">
            <!--图片-->
            <div class="imgs">
            <a href="/details.html?itemid=${cart.itemid }">
            	<img src="https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i4/1258106044788429247/TB2OoeaqbFlpuFjy0FgXXbRBVXa_!!0-saturn_solar.jpg_180x180.jpg_.webp" style="width: 170px;height: 170px;" alt="">
            </a>
                
            </div>
            <!--左侧列表-->
            <div class="leftlab">
                <div style="display: inline-block">
                	<a href="/details.html?itemid=${cart.itemid }">${cart.itemName}</a>
                </div>
                <div>价格:${cart.price/100 }</div>
                <div>邮费:${cart.postage/100 }</div>
                <div>剩余:${cart.quantity }</div>
            </div>

        </div>
        <!--右边-->
        <div class="rightlab">
            <div><a href="#">购买</a></div>
            <div><a href="/cart/deletecart.html?itemid=${cart.itemid }" >删除商品</a></div>
            <div><a href="#">进入店铺</div>
        </div>
    </div>
  </c:forEach>
  	  
	  <div class="footer">
	  		<input type="submit" value="购买"></input>
			
	  </div>
  </form>
    <!--foreach 结束-->

</div>
</body>
<script src="${pageContext.request.contextPath }/js/jquery-1.7.1.min.js"></script>

  <script type="text/javascript">
        $(function() {
           $("#AllcheckBox").click(function() {
                $('input[name="itemid"]').attr("checked",this.checked); 
            });
            var $subBox = $("input[name='itemid']");
            $subBox.click(function(){
                $("#AllcheckBox").attr("checked",$subBox.length == $("input[name='itemid']:checked").length ? true : false);
            });
        });
    </script>

</html>
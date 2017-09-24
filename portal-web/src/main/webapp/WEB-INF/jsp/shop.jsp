<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
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
			border: 1px solid orangered;
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
			border: 1px solid orangered;
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
        border: 1px solid orangered;
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
<div class="header">
	<h1 class="title">电商秒杀系统<h1>
		
	    <div class="rightlable">
           <!-- 登录-->
		   <div><a href="/shop/SelectAndUserId.html">我的店铺</a></div>
			<div><a href="#">购物车</a></div>
			<div><a href="#">我的订单</a></div>
			<div><a href="#">欢迎${sessionScope.user.username }登录</a></div>
        </div>
</div >

<div class="shop">
	<h3 class="shopname">${shop.shopName}店铺名<h3>
	<button class="button" id="publishbut">发布商品</button>
</div>

<!--外层div-->

<div class="out">
    <!--如果你是jsp页面的话直接通过foreach循环输出即可-->
    <!--foreach(){-->
    <c:forEach items="${itemlist}" var="item">
    <div class="list">
        <!--左边-->
        <div class="left">
            <!--图片-->
            <div class="imgs">
                <img src="https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i4/1258106044788429247/TB2OoeaqbFlpuFjy0FgXXbRBVXa_!!0-saturn_solar.jpg_180x180.jpg_.webp" style="width: 170px;height: 170px;" alt="">
            </div>
            <!--左侧列表-->
            <div class="leftlab">
                <div style="display: inline-block">${item.itemName }</div> 
                	<c:if test="${not empty item.seckill }">
                		<button >秒杀</button>
                	</c:if>
                <div>价格:${item.price/100 }</div>
                <div>邮费:${item.postage/100 }</div>
                <div>剩余:${item.quantity } 件</div>
            </div>

        </div>
        
        <!--右边-->
        <div class="rightlab">
            <div><a href="#">编辑商品</a></div>
            <div><a href="#">删除商品</a></div>
            <div>&nbsp;</div>
            <div>销量:10 件</div>
        </div>
    </div>
  </c:forEach>
    <!--foreach 结束-->

</div>
</body>
<script src="/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
$("#publishbut").click(function () {
	window.location.href="/publish.html"	
});



</script>
</html>
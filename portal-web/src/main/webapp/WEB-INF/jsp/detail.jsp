<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
    <!DOCTYPE html>
<html>
  <head>
    <title>detail</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link href="css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
    <link href="css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
    <link href="files/detail/styles.css" type="text/css" rel="stylesheet"/>
  </head>
  <body>
  <style type="text/css">
  	a{text-decoration:none}
  </style>
  
    <div id="base" class="">

      <!-- Unnamed (Rectangle) -->
      <div id="u97" class="ax_default box_1">
        <div id="u97_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u98" class="text" style="display:none; visibility: hidden">
          <p><span></span></p>
        </div>
      </div>

      <!-- Unnamed (Horizontal Line) -->
      <div id="u99" class="ax_default line">
        <img id="u99_img" class="img " src="images/detail/u99.png"/>
        <!-- Unnamed () -->
        <div id="u100" class="text" style="display:none; visibility: hidden">
          <p><span></span></p>
        </div>
      </div>

      <!-- Unnamed (Group) -->
      <div id="u101" class="ax_default" data-left="121" data-top="103" data-width="843" data-height="53">

        <!-- Unnamed (Rectangle) -->
        <div id="u102" class="ax_default box_3">
          <div id="u102_div" class=""></div>
          <!-- Unnamed () -->
          <div id="u103" class="text" style="display:none; visibility: hidden">
            <p><span></span></p>
          </div>
        </div>

        <!-- Unnamed (Rectangle) -->
        <div id="u104" class="ax_default label">
          <div id="u104_div" class=""></div>
          <!-- Unnamed () -->
          <div id="u105" class="text">
            <p><span>电商秒杀平台</span></p>
          </div>
        </div>

        <!-- Unnamed (Rectangle) -->
        
        <div id="u106" class="ax_default label">
        
          <div id="u106_div" class=""></div>
		 
          <!-- Unnamed () -->
          <c:if test="${empty sessionScope.user }">
	          <div id="u107" class="text">
	            <p><span>
	            <a href="/login.html"> 登录</a>
	           </span></p>
	          </div>
          </c:if>
          <c:if test="${not empty sessionScope.user }">
		  </c:if>
        </div>
        

        <!-- Unnamed (Rectangle) -->
        <div id="u108" class="ax_default label">
          <div id="u108_div" class=""></div>
          <!-- Unnamed () -->
          <c:if test="${empty sessionScope.user }">
	          <div id="u109" class="text">
	            <p><span>
	            <a href="/regist.html">  注册</a>
	           </span></p>
	          </div>
          </c:if>
          <c:if test="${not empty sessionScope.user }">
			<div><a href="/user/logout.html">退出</a></div>
		  </c:if>
        </div>
      </div>
		
		
      <!-- Unnamed (Rectangle) -->
      <div id="u110" class="ax_default label">
        <div id="u110_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u111" class="text">
          <p><span>${item.shopName }</span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u112" class="ax_default label">
        <div id="u112_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u113" class="text">
          <p><span>价格：${item.price/100 }</span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u114" class="ax_default label">
        <div id="u114_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u115" class="text">
          <p><span>邮费：${item.postage/100 }</span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u116" class="ax_default label">
        <div id="u116_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u117" class="text">
          <p><span>剩余：${item.quantity }件</span></p>
        </div>
      </div>

      <!-- Unnamed (Horizontal Line) -->
      <div id="u118" class="ax_default line">
        <img src="" id="u118_img" class="img " />
        <!-- Unnamed () -->
        <div id="u119" class="text" style="display:none; visibility: hidden">
          <p><span></span></p>
        </div>
      </div>

      <!-- Unnamed (HTML Button) -->
      <div id="u120" class="ax_default html_button">
        <input id="u120_input" type="submit" onclick="addCart('${item.itemid}')" value="加入购物车"/>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u121" class="ax_default image">
        <img src = "${item.image }" id="u121_img" class="img "/>
        <!-- Unnamed () -->
        <div id="u122" class="text" style="display:none; visibility: hidden">
          <p><span></span></p>
        </div>
      </div>
      <form action="/order/orderadd.html" method="post">
          <input type="hidden" name="itemid" value="${item.itemid }"></input>
	      <!-- Unnamed (HTML Button) -->
	      <div id="u123" class="ax_default html_button">
	        <input id="u123_input" type="submit"  value="购买"/>
	      </div>
      </form>

      <!-- Unnamed (Rectangle) -->
      <div id="u124" class="ax_default label">
        <div id="u124_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u125" class="text">
          <p><span>离开始还剩</span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u126" class="ax_default paragraph">
        <div id="u126_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u127" class="text">
          <p><span>${item.comments }</span></p>
        </div>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u128" class="ax_default image">
        <img  id="u128_img" class="img " src="images/detail/u128.png"/>
        <!-- Unnamed () -->
        <div id="u129" class="text" style="display:none; visibility: hidden">
          <p><span></span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u130" class="ax_default link_button">
        <div id="u130_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u131" class="text">
          <p><span>
         	 <a href = "/index.html">返回</a>
          </span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u132" class="ax_default box_3">
        <div id="u132_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u133" class="text">
          <p><span>秒杀</span></p>
        </div>
      </div>
    </div>
  </body>
  <script src="js/jquery.min.js"></script>
  <script type="text/javascript">
  function	addCart(itemid){
	  $.post("/cart/insertcart.html",{"itemID":itemid},function(data){
		  if(data=="true"){
			  alert("购物车添加成功")
				
		  }else{
			  alert("购物车添加失败")
		  }
	  });
	  
  }
  </script>
</html>
    
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>login</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link href="css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
    <link href="css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
    <link href="files/login/styles.css" type="text/css" rel="stylesheet"/>
  </head>
  <body>
    <div id="base" class="">

      <!-- Unnamed (Rectangle) -->
      <div id="u74" class="ax_default box_1">
        <div id="u74_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u75" class="text" style="display:none; visibility: hidden">
          <p><span></span></p>
        </div>
      </div>

      <!-- Unnamed (Group) -->
      <div id="u76" class="ax_default" data-left="205" data-top="105" data-width="843" data-height="53">

        <!-- Unnamed (Rectangle) -->
        <div id="u77" class="ax_default box_3">
          <div id="u77_div" class=""></div>
          <!-- Unnamed () -->
          <div id="u78" class="text" style="display:none; visibility: hidden">
            <p><span></span></p>
          </div>
        </div>

        <!-- Unnamed (Rectangle) -->
        <div id="u79" class="ax_default label">
          <div id="u79_div" class=""></div>
          <!-- Unnamed () -->
          <div id="u80" class="text">
            <p><span>电商秒杀平台</span></p>
          </div>
        </div>

        <!-- Unnamed (Rectangle) -->
        <div id="u81" class="ax_default label">
          <div id="u81_div" class=""></div>
          <!-- Unnamed () -->
          <div id="u82" class="text">
            <p><span>登录</span></p>
          </div>
        </div>

        <!-- Unnamed (Rectangle) -->
        <div id="u83" class="ax_default label">
          <div id="u83_div" class=""></div>
          <!-- Unnamed () -->
          <div id="u84" class="text">
            <p><span>注册</span></p>
          </div>
        </div>
      </div>

      <!-- Unnamed (Horizontal Line) -->
      <div id="u85" class="ax_default line">
        <img id="u85_img" class="img " src="images/login/u85.png"/>
        <!-- Unnamed () -->
        <div id="u86" class="text" style="display:none; visibility: hidden">
          <p><span></span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u87" class="ax_default label">
        <div id="u87_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u88" class="text">
          <p><span>会员登录</span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u89" class="ax_default label">
        <div id="u89_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u90" class="text">
          <p><span>*用户名:</span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u91" class="ax_default label">
        <div id="u91_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u92" class="text">
          <p><span>*密码:</span></p>
        </div>
      </div>
	<form id="form">
      <!-- username (Text Field) -->
      <div id="u93" class="ax_default text_field" data-label="username">
        <input id="u93_input" type="text" value="" name="username" placeholder="4-20位由汉字或数字和字母组成，首位必须为字母"/>
      </div>

      <!-- password (Text Field) -->
      <div id="u94" class="ax_default text_field" data-label="password">
        <input id="u94_input" type="password" value="" name="pwd" placeholder="6-12个数字和字母的组合"/>
      </div>
         <!-- Unnamed (HTML Button) -->
      <div id="u96" class="ax_default html_button">
        <input id="u96_input" type="button" value="登录"/>
      </div>
	</form>
      <!-- Unnamed (HTML Button) -->
      <div id="u95" class="ax_default html_button">
     
        <input id="u95_input" type="button" value="回到首页" />
      </div>

   
    </div>
  </body>
  <script src="js/jquery-1.7.1.min.js"></script>
  <script type="text/javascript">
  	var url ="${url}";
  	$(function(){
  		
  		$("#u96_input").click(function(){
  			$.post("/user/login.html",$("#form").serialize(),function(data){
				var d= JSON.parse(data);
  				if(d.status==200){
  					if(url==""){
  						window.location.href="/index.html";
  					}else{
  						window.location.href=url;
  					}
  					
  				}else{
  					alert(d.message);
  				}
  			});
  			
  		});

  		//
  		$("#u95_input").click(function(){
  			
  			window.location.href="/";
  		});
  		
  	  	$("#u84").click(function(){
  			
  			window.location.href="/regist.html";
  		});
  	 	 var reg  = /^[a-zA-Z][\u4E00-\u9FA5\uF900-\uFA2D\a-zA-Z0-9]{3,20}$/;
		 var reg1 = /^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{6,12}$/;
		$("#u93_input").change(function(){
			var username = $("#u93_input").val();
			
			if (reg.test(username)){
				
			}
			else{
				$("#u93_input").val("");
				alert("不合格！用户名由4-20位由汉字或数字和字母组成，首位必须为字母");
			}
			
		})
		$("#u94_input").change(function(){
			var pwd = $("#u94_input").val();
			
			if (reg1.test(pwd)){
				
			}
			else{
				$("#u94_input").val("");
				alert("不合格！密码由6-12位由数字和字母组成");
			}
			
		})
  		
  		
  	})
  
  </script>
</html>
    
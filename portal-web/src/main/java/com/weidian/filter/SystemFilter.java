package com.weidian.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 拦截器 判断用户是否登陆 
 * @author Administrator
 *
 */
public class SystemFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest httpRequest = (HttpServletRequest) request;
		 HttpServletResponse httpResponse =(HttpServletResponse) response;
         String requestURl=    httpRequest.getRequestURI(); // 请求路径

      
        if(("/").equals(requestURl)|| requestURl.indexOf("/index.html")>=0 || requestURl.indexOf("/login.html")>=0 || requestURl.indexOf("/detail.html")>=0 || requestURl.indexOf("/regist.html")>=0){
       	  	chain.doFilter(httpRequest, response);  
         }else if(httpRequest.getSession().getAttribute("user")==null){
        	 	  request.setAttribute("url", requestURl+"?"+httpRequest.getQueryString());
				  request.getRequestDispatcher("/tologin.html").forward(request,response);
		 }
//         else if( requestURl.indexOf("/cart")>=0 || requestURl.indexOf("/item")>=0 || requestURl.indexOf("/order")>=0 || requestURl.indexOf("/secKill")>=0){
//        	 request.setAttribute("url", requestURl+"?"+httpRequest.getQueryString());
//			  request.getRequestDispatcher("/tologin.html").forward(request,response);
//         }
         else{
				 chain.doFilter(httpRequest, response);
			}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}

package com.weidian.log.LogAspect;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.After;

import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.weidian.log.annotation.Log;
import com.weidian.log.pojo.SystemLog;
import com.weidian.pojo.User;

@Aspect
@Component
public class SystemLogAspect {
 
/**
 * @Before("controllerAspect()")  前置通知
 * 
 @Around("controllerAspect()")  配置controller环绕通知,使用在方法aspect()上注册的切入点
 * 
 *  @AfterThrowing(pointcut = "controllerAspect()", throwing="e")  异常通知 用于拦截记录异常日志
 */
    
    private  static  final Logger logger = LoggerFactory.getLogger(SystemLogAspect. class);  
  
   //Controller层切点  
     @Pointcut("execution (* com.weidian.portal.controller.*.*(..))")  
    public  void controllerAspect() {  
     }  
   

   
     /** 
85      * 后置通知 用于拦截Controller层记录用户的操作 
86      * 
87      * @param joinPoint 切点 
88      */  
   @After("controllerAspect()")  
    public  void after(JoinPoint joinPoint) {  

	   //System.out.println("result"+result);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  
        System.out.println("a="+request.getParameter("a"));
        HttpSession session = request.getSession();
         //读取session中的用户  
         User user = (User) session.getAttribute("user");  
         //请求的IP  
         String ip = request.getRemoteAddr();
     
         try {  
             
            String targetName = joinPoint.getTarget().getClass().getName();  
            String methodName = joinPoint.getSignature().getName();  
            Object[] arguments = joinPoint.getArgs();  
            Class targetClass = Class.forName(targetName);  
             Method[] methods = targetClass.getMethods();
             String operationType = "";
             String operationName = "";
             for (Method method : methods) {  
                 if (method.getName().equals(methodName)) {  
                     Class[] clazzs = method.getParameterTypes();  
                      if (clazzs.length == arguments.length) {  
                          operationType = method.getAnnotation(Log.class).operationType();
                          operationName = method.getAnnotation(Log.class).operationName();
                          break;  
                     }  
                 }  
            }
             //*========控制台输出=========*//  
            System.out.println("=====controller后置通知开始=====");  
            System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")+"."+operationType);  
            System.out.println("方法描述:" + operationName);  
           //  System.out.println("请求人:" + user.getName());  
             System.out.println("请求IP:" + ip);  
            //*========数据库日志=========*//  
            SystemLog log = new SystemLog();  
            log.setId(UUID.randomUUID().toString());
            log.setDescription(operationName);  
             log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")+"."+operationType);  
             log.setLogType((long)0);  
            log.setRequestIp(ip);  
             log.setExceptioncode( null);  
             log.setExceptionDetail( null);  
             log.setParams( null);  
            //log.setCreateBy(user.getName());  
             log.setCreateDate(new Date());  
             //保存数据库  
             //systemLogService.insert(log);  
             System.out.println("=====controller后置通知结束=====");  
         }  catch (Exception e) {  
        	 System.out.println("erterterterterterterter");
             //记录本地异常日志  
             logger.error("==后置通知异常==");  
             logger.error("异常信息:{}", e.getMessage());  
        }  
   } 
     
     
//     /** 
//159      * 异常通知 用于拦截记录异常日志 
//160      * 
//161      * @param joinPoint 
//162      * @param e 
//163      */  
//      @AfterThrowing(pointcut = "controllerAspect()", throwing="e")  
//     public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {  
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  
//         HttpSession session = request.getSession();  
//         //读取session中的用户  
//         User user = (User) session.getAttribute("user");  
//         //获取请求ip  
//        String ip = request.getRemoteAddr();  
//         //获取用户请求方法的参数并序列化为JSON格式字符串  
//         
////         User user = new User();
////         user.setId(1);
////         user.setName("张三");
////         String ip = "127.0.0.1";
////         
////        String params = "";  
////         if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {  
////              for ( int i = 0; i < joinPoint.getArgs().length; i++) {  
////                 params += JsonUtil.getJsonStr(joinPoint.getArgs()[i]) + ";";  
////             }  
////        }  
//         try {  
//              
////             String targetName = joinPoint.getTarget().getClass().getName();  
////              String methodName = joinPoint.getSignature().getName();  
////              Object[] arguments = joinPoint.getArgs();  
////             Class targetClass = Class.forName(targetName);  
////              Method[] methods = targetClass.getMethods();
////              String operationType = "";
////               for (Method method : methods) {  
////                   if (method.getName().equals(methodName)) {  
////                      Class[] clazzs = method.getParameterTypes();  
////                       if (clazzs.length == arguments.length) {  
////                           operationType = method.getAnnotation(Log.class).operationType();
////                           operationName = method.getAnnotation(Log.class).operationName();
////                           break;  
////                      }  
////                 }  
////             }
////              /*========控制台输出=========*/  
////             System.out.println("=====异常通知开始=====");  
////             System.out.println("异常代码:" + e.getClass().getName());  
////            System.out.println("异常信息:" + e.getMessage());  
////             System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")+"."+operationType);  
////             System.out.println("方法描述:" + operationName);  
////             System.out.println("请求人:" + user.getName());  
////             System.out.println("请求IP:" + ip);  
////             System.out.println("请求参数:" + params);  
////                /*==========数据库日志=========*/  
////             SystemLog log = new SystemLog();
////             log.setId(UUID.randomUUID().toString());
////             log.setDescription(operationName);  
////            log.setExceptioncode(e.getClass().getName());  
////             log.setLogType((long)1);  
////             log.setExceptionDetail(e.getMessage());  
////             log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));  
////            log.setParams(params);  
////             log.setCreateBy(user.getName());  
////            log.setCreateDate(new Date());  
////            log.setRequestIp(ip);  
////            //保存数据库  
////            systemLogService.insert(log);  
//             System.out.println("=====异常通知结束=====");  
//         }  catch (Exception ex) {  
//             //记录本地异常日志  
//            logger.error("==异常通知异常==");  
//             logger.error("异常信息:{}", ex.getMessage());  
//         }  
//          /*==========记录本地异常日志==========*/  
//       // logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);  
//   
//     }  
     
 }

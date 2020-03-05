<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>蓝闪快跑</title>
    <link type="text/css" rel="stylesheet" href="/css/style.css" />
    <link type="text/css" rel="stylesheet" href="/css/public.css" />
</head>
<body>
<!--头部-->
    <header class="publicHeader">
        <h1>SSM练习系统</h1>
        <div class="publicHeaderR">
            <p><span>您好！</span><span style="color: #fff21b"> ${userSession.username}</span> , 欢迎你！</p>
            <a href="/user/logout">退出</a>
        </div>
    </header>
<!--时间-->
    <section class="publicTime">
        <marquee behavior="left" direction="left"><h4>本系统是蓝闪同学用于练习日常开发工作中一些技术和知识点</h4></marquee>
    </section>
 <!--主体内容-->
 <section class="publicMian ">
     <div class="left">
         <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
         <nav>
             <ul class="list">
                 <%--<li ><a href="/jsp/bill.do?method=query">订单管理</a></li>--%>
              <%--<li><a href="/provider/providerlist.html">供应商管理</a></li>--%>
              <li><a href="/user/userlist">用户管理</a></li>
              <li><a href="/jsp/pwdmodify.jsp">密码修改</a></li>
              <li><a href="/user/logout">退出系统</a></li>
             </ul>
         </nav>
     </div>

     <input type="hidden" id="path" name="path" value=""/>
     <input type="hidden" id="referer" name="referer" value="<%=request.getHeader("Referer")%>"/>


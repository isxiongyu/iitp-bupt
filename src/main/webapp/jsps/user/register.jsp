<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<div align="center">
		<h1>用户注册页面</h1>
		<font color="red">${registerError.message }</font>
		<form action="<c:url value='/user/register.do'/>" method="POST">
			用户名：<input type="text" name="username" value="${user.username }"/></br>
			密码：<input type="password" name="password" value="${user.password }"/><br/>
			邮箱：<input type="text" name="email" value="${user.email}"/></br>
			手机号：<input type="text" name="phone" value="${user.phone }"/></br>
			用户描述：<input type="text" name="userDescription" value="${user.userDescription }"/></br>
			性别：男<input type="radio" name="sex" value="M"/>
			女<input type="radio" name="sex" value="F"/></br>
			入学年份：<input type="date" min="1970-01-01" max="2099-12-31" name="year" value="${user.year}"></br>
			公寓号：<input type="text" name="department" value="${user.department}"></br>
			<input type="submit" value="注册"/>
		</form>
	</div>
  </body>
</html>

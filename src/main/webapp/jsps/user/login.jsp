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
    
    <title>My JSP 'login.jsp' starting page</title>
    
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
		<h2>北邮闲置商品交易平台用户登录页面</h2>
		<font color="red">${loginError }</font>
		<form action="<c:url value='/user/login.do'/>" method="POST">
			<table align="center">
				<tr>
					<td>
						用户名：
					</td>
					<td>
						<input type="text" name="name" value="${name }"/>
					</td>
				</tr>
				<tr>
					<td>
						密码：
					</td>
					<td>
						<input type="text" name="password" value="${password }"/>
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<input type="submit" value="登录"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
  </body>
</html>

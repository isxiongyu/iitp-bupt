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
		<h2>北邮闲置商品交易平台用户注册页面</h2>
		<font color="red">${registerError }</font>
		<form action="<c:url value='/user/register.do'/>" method="POST">
			<table align="center">
				<tr>
					<td>
						用户名：
					</td>
					<td>
						<input type="text" name="username" value="${user.username }"/>
					</td>
					<td>
						<font color="red">${nameErrorMsg }</font>
					</td>
				</tr>
				<tr>
					<td>
						密码：
					</td>
					<td>
						<input type="password" name="password" value="${user.password }"/>
					</td>
					<td>
					</td>
				</tr>
				<tr>
					<td>
						手机号：
					</td>
					<td>
						<input type="text" name="phone" value="${user.phone }"/>
					</td>
					<td>
						<font color="red">${phoneErrorMsg }</font>
					</td>
				</tr>
				<tr>
					<td>
						邮箱：
					</td>
					<td>
						<input type="text" name="email" value="${user.email}"/>
					</td>
					<td>
						<font color="red">${emailErrorMsg }</font>
					</td>
				</tr>
				<tr>
					<td>
						用户描述：
					</td>
					<td>
						<input type="text" name="userDescription" value="${user.userDescription }"/>
					</td>
					<td>
					</td>
				</tr>
				<tr>
					<td>
						性别：
					</td>
					<td>
						男<input type="radio" name="sex" value="M"/>
						女<input type="radio" name="sex" value="F"/>
					</td>
					<td>
						<font color="red">${sexErrorMsg }</font>
					</td>
				</tr>
				<tr>
					<td>
						入学时间：
					</td>
					<td>
						<input type="date" min="1970-01-01" max="2099-12-31" name="year" value="${user.year}">
					</td>
					<td>
						<font color="red">${yearErrorMsg }</font>
					</td>
				</tr>
				<tr>
					<td>
						公寓号：
					</td>
					<td>
						<input type="text" name="department" value="${user.department}">
					</td>
					<td>
						<font color="red">${departmentErrorMsg }</font>
					</td>
				</tr>
<%--				密码：<input type="password" name="password" value="${user.password }"/><br/>--%>
<%--				邮箱：<input type="text" name="email" value="${user.email}"/>--%>
<%--				<font color="red">${emailErrorMsg }</font></br>--%>
<%--				手机号：<input type="text" name="phone" value="${user.phone }"/>--%>
<%--				<font color="red">${phoneErrorMsg }</font></br>--%>
<%--				用户描述：<input type="text" name="userDescription" value="${user.userDescription }"/></br>--%>
<%--				性别：男<input type="radio" name="sex" value="M"/>--%>
<%--				女<input type="radio" name="sex" value="F"/>--%>
<%--				<font color="red">${sexErrorMsg }</font></br>--%>
<%--				入学年份：<input type="date" min="1970-01-01" max="2099-12-31" name="year" value="${user.year}">--%>
<%--				<font color="red">${yearErrorMsg }</font></br>--%>
<%--				公寓号：<input type="text" name="department" value="${user.department}">--%>
<%--				<font color="red">${departmentErrorMsg }</font></br>--%>
				<tr>
					<td colspan="3" align="center">
						<input type="submit" value="注册"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
  </body>
</html>

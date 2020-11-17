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
    
    <title>My JSP 'main.jsp' starting page</title>
    
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
	<table border="1" bordercolor="red" cellspacing="0"  align="center" width="100%" height="100%">
		<tr style="height:50px">
			<td colspan="3" >
				<h1 style="color: red" align="center">欢迎光临北邮闲置物品交易平台</h1>
			</td>
		</tr>
		<tr style="height:120px">
			<td>
				<c:choose>
					<c:when test="${empty sessionScope.user}">
						<a href="<c:url value='/jsps/user/register.jsp'/>">还没有账号？点击注册</a></br>
						<a href="<c:url value='/jsps/user/login.jsp'/>">已有账号？点击登录</a>
					</c:when>
					<c:otherwise>
						<span>欢迎用户${sessionScope.user.username}</span>
<%--						<a href="<c:url value='/user/quit.do'/>" target="_parent">退出</a>--%>
<%--						<a href="<c:url value='/cart/myCart.do?uid=${sessionScope.user.uid }'/>" target="right">我的购物车</a>--%>
<%--						<a href="<c:url value='/order/myOrder.do?uid=${sessionScope.user.uid }'/>" target="right">我的订单</a>--%>
					</c:otherwise>
				</c:choose>
    		</td>
			<td>
				<a href="<c:url value='/user/addGoodsLink.do'/>">发布物品</a>
			</td>
		</tr>
		<tr>
    		<td style="width:160px;" valign="top" align="center">
<%--    			<iframe frameborder="0" height="100%" width="100%" src="<c:url value='/book/selAllCategory.do'/>" name="left"></iframe>--%>
    		</td>
    		<td >
<%--    			<iframe frameborder="0" height="100%" width="100%" src="<c:url value='/jsps/right.jsp'/>" name="right"></iframe>--%>
    		</td>
    	</tr>
	</table>
  </body>
</html>

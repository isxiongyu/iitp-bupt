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
		<h2>北邮闲置商品交易平台修改密码</h2>
		<form action="<c:url value='/user/modPassword.do'/>" method="POST">
			<input type="hidden" name="email" value="${email}">
			<table align="center">
				<tr>
					<td>
						新密码：
					</td>
					<td>
						<input type="text" name="newPassword" value="${newPassword }"/>
					</td>
				</tr>
				<tr>
					<td>
						确认新密码：
					</td>
					<td>
						<input type="text" name="confirmPassword" value="${confirmPassword }"/>
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<input type="submit" value="确定"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
<%--  	<script type="application/javascript">--%>
<%--		function send() {--%>
<%--			var email = document.getElementById("email").value;--%>
<%--			var href = window.document.location.href;--%>
<%--			var pathname = window.document.location.pathname;--%>
<%--			var contextPath = href.substring(0, href.indexOf(pathname));--%>
<%--			var url = contextPath + "/iitp/user/sendVerifyCode.do?email=" + email;--%>
<%--			var request = new XMLHttpRequest();--%>
<%--			request.open("GET", url, true);--%>
<%--			request.send();--%>
<%--		}--%>
<%--	</script>--%>
  </body>
</html>

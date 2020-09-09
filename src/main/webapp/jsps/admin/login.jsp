<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/9/8
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员登录</title>
</head>
<body>
<div align="center">
    <form action="${pageContext.request.contextPath}/login.do" method="post">
        <p>管理员登录</p>
        <table>
            <tr>
                <td>用户名：</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td>密 码：</td>
                <td><input type="text" name="password"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="登录"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>

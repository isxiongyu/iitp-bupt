<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>

</head>

<body>
<table border="1" bordercolor="red" cellspacing="0" align="center" width="100%" height="100%">
    <tr style="height:50px">
        <td colspan="3">
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
            <ol id="olCategory">

            </ol>
        </td>
        <td>
            <table align="center">
                <tr>
                    <c:forEach items="${goodsList}" var="goods">
                        <td>
                            <div align="center">
                                <img src="<c:url value='/image/${goods.img}'/>" alt="物品图片" height="220" width="180">
                            </div>
                        </td>
                    </c:forEach>
                </tr>
                <tr align="left">
                    <c:forEach items="${goodsList}" var="goods">
                        <td width="180">
                            <ul>
                                <li>物品名：${goods.name}</li>
                                <li>物品描述：${goods.description}</li>
                                <li>价格：${goods.price}元</li>
                                <li>库存：${goods.rest}件</li>
                            </ul>
                        </td>
                    </c:forEach>
                </tr>
            </table>
        </td>
    </tr>
</table>
<script type="text/javascript">
    //创建ajax引擎
    function getXmlHttpObject() {
        var xmlHttpRequest;
        if (window.ActiveObject) {
            xmlHttpRequest = new ActiveObject("Microsoft.XMLHTTP");
        } else {
            xmlHttpRequest = new XMLHttpRequest();
        }
        return xmlHttpRequest;
    }

    var myXmlHttpRequest = null;
    window.onload = function () {
        myXmlHttpRequest = getXmlHttpObject();

        if (myXmlHttpRequest != null) {
            myXmlHttpRequest.open("GET", "<c:url value='/common/main.do'/>", true);//异步方式
            myXmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            //指定回调函数
            myXmlHttpRequest.onreadystatechange = detail;
            //发送
            myXmlHttpRequest.send();
        }
    };

    function detail() {
        var href = window.document.location.href;
        var pathname = window.document.location.pathname;
        var contextPath = href.substring(0, href.indexOf(pathname));
        if (myXmlHttpRequest.readyState === 4) {
            if (myXmlHttpRequest.status === 200) {
                //取出服务器回送的数据
                var mainResponse = myXmlHttpRequest.responseText;
                var mainResponseEval = eval('(' + mainResponse + ')');
                var categoryVos = mainResponseEval.categoryVos;
                var olCategoryEle = document.getElementById("olCategory");
                for (var i = 0; i < categoryVos.length; i++) {
                    var myli = document.createElement("li");
                    var myA = document.createElement("a");
                    var u = "<c:url value='/goods/getGoodsByCategoryIdPage.do'/>" + "?categoryId=" + categoryVos[i].id + "&pageSize=5&page=1";
                    myA.href = u;
                    myA.innerText = categoryVos[i].name;
                    olCategoryEle.append(myli);
                    myli.append(myA);
                    var categorySeconds = categoryVos[i].categorySeconds;
                    var myliol = document.createElement("ol");
                    myli.append(myliol);
                    for (var j = 0; j < categorySeconds.length; j++) {
                        var myliolli = document.createElement("li");
                        var myASecond = document.createElement("a");
                        myliol.append(myliolli);
                        myliolli.append(myASecond);
                        var url = "<c:url value='/goods/getGoodsByCategorySecondIdPage.do'/>" + "?categorySecondId=" + categorySeconds[j].id + "&pageSize=5&page=1";
                        myASecond.href = url;
                        myASecond.innerText = categorySeconds[j].name;
                    }
                }
            }
        }
    }
</script>
</body>
</html>

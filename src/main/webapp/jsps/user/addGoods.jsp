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

    <title>My JSP 'login.jsp' starting page</title>

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
<div align="center">
    <h2>北邮闲置商品交易平台发布物品</h2>
    <font color="red">${addGoodsError }</font>
    <table align="center">
        <tr>
            <td>
                所属一级分类：
            </td>
            <td>
                <select id="categorySelect" onchange="selectCategory(this.value)">
                    <option value="">请选择一级类别</option>
                    <c:forEach items="${categories }" var="myCategory">
                        <option value="${myCategory.id}">${myCategory.name}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <font color="red">${goodCategoryIdErrorMsg }</font>
            </td>
        </tr>
        <tr>
            <td>
                所属二级分类：
            </td>
            <td>
                <select id="categorySecondSelect" onchange="selectSecondCategory(this.value)">
                </select>
            </td>
            <td>
                <font color="red">${categorySecondIdErrorMsg }</font>
            </td>
        </tr>
        <form action="<c:url value='/user/addGoods.do'/>" method="POST" enctype="multipart/form-data">
            <tr>
                <td>
                    物品图片：
                </td>
                <td>
                    <input type="file" name="image"/>
                </td>
                <td>
                    <font color="red">${imgErrorMsg }</font>
                </td>
            </tr>
            <tr>
                <td>
                    物品名称：
                </td>
                <td>
                    <input type="text" name="name" value="${goods.name }"/>
                </td>
                <td>
                    <font color="red">${nameErrorMsg }</font>
                </td>
            </tr>
            <tr>
                <td>
                    物品描述：
                </td>
                <td>
                    <input type="text" name="description" value="${goods.description }"/>
                </td>
                <td>
                    <font color="red">${descriptionErrorMsg }</font>
                </td>
            </tr>
            <tr>
                <td>
                    物品价格：
                </td>
                <td>
                    <input type="text" name="price" value="${goods.price }"/>
                </td>
                <td>
                    <font color="red">${priceErrorMsg }</font>
                </td>
            </tr>
            <tr>
                <td>
                    物品个数：
                </td>
                <td>
                    <input type="text" name="rest" value="${goods.rest }"/>
                </td>
                <td>
                    <font color="red">${restErrorMsg }</font>
                </td>
            </tr>
            <input type="hidden" id="categoryId" name="goodCategoryId" value=""/>
            <input type="hidden" id="categorySecondId" name="categorySecondId" value=""/>
            <tr>
                <td colspan="3" align="center">
                    <input type="submit" value="提交"/>
                </td>
            </tr>
        </form>
    </table>
</div>
<script type="text/javascript">
    function selectSecondCategory(secondCategoryId) {
        var csidElement = document.getElementById("categorySecondId");
        csidElement.value = secondCategoryId;
    }

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

    function selectCategory(cid) {
        var cidElement = document.getElementById("categoryId");
        cidElement.value = cid;
        myXmlHttpRequest = getXmlHttpObject();

        if (myXmlHttpRequest != null) {
            var href = window.document.location.href;
            var pathname = window.document.location.pathname;
            var contextPath = href.substring(0, href.indexOf(pathname));
            var url = contextPath + "<c:url value='/categorySecond/getCategorySecondAJAX.do'/>";//post方式提交
            var data = "categoryId=" + $("#categorySelect").val();
            myXmlHttpRequest.open("post", url, true);//异步方式
            myXmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            //指定回调函数
            myXmlHttpRequest.onreadystatechange = detail;
            //发送
            myXmlHttpRequest.send(data);
        }
    }

    function detail() {

        if (myXmlHttpRequest.readyState === 4) {
            if (myXmlHttpRequest.status === 200) {

                //取出服务器回送的数据
                var categorySeconds = myXmlHttpRequest.responseText;

                var categorySecondsEval = eval('(' + categorySeconds + ')');  // eval();方法

                //解决方案是添加如下代码
                var csEle = $("#categorySecondSelect");
                csEle.empty();
                var myOption = document.createElement("option");
                myOption.innerText = "--请选择二级类别--";
                //添加到
                csEle.append(myOption);
                //解决方案
                //遍历并取出城市
                for (var i = 0; i < categorySecondsEval.length; i++) {
                    //这里出现了一个问题，每次点击省份，市的下拉列表中的内容会不断增加（重复),这是因为没有刷新，解决方案：将

                    var name = categorySecondsEval[i].name;
                    var id = categorySecondsEval[i].id;
                    //创建新的元素option
                    myOption = document.createElement("option");
                    myOption.value = id;
                    myOption.innerText = name;
                    //添加到
                    csEle.append(myOption);
                    //在一个元素中添加另一个元素使用appendChild()；
                }
            }
        }
    }
</script>
</body>
</html>

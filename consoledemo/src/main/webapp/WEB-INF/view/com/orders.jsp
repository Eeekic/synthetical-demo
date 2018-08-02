<%--
  Created by IntelliJ IDEA.
  User: SUNPENG
  Date: 2018/8/1
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>订单信息</title>
    <style type="text/css">
        .ordersTableStyle{
            width:150px;
            align:center;
        }
        .ordersTableStyleLong{
            width:200px;
            align:center;
        }
    </style>
</head>
<body>
    <jsp:include page="commonHeaderBanner.jsp"/>
    <p></p>
    <p></p>
    <p></p>
    <table align="center">
        <tr>
            <td class="ordersTableStyle"><h3>订单号</h3></td>
            <td class="ordersTableStyle"><h3>商品图像</h3></td>
            <td class="ordersTableStyleLong"><h3>名称</h3></td>
            <td class="ordersTableStyle"><h3>价格</h3></td>
            <td class="ordersTableStyleLong"><h3>日期</h3></td>
        </tr>
        <c:forEach items="${ordersList }" var="orders" varStatus="status">
            <tr>
                <td class="ordersTableStyle">${orders.ordersId}</td>
                <td class="ordersTableStyle">
                    <img src="${orders.goodsPicturePath}"  width="70" height="70">
                </td>
                <td class="ordersTableStyleLong">${orders.goodsName}</td>
                <td class="ordersTableStyle">${orders.goodsPrice}</td>
                <td class="ordersTableStyleLong">${orders.ordersDate}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

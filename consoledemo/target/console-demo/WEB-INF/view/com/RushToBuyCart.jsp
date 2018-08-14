<%--
  Created by IntelliJ IDEA.
  User: lizhi
  Date: 2018/8/14
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>RushToBuyCart</title>
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
<table align="center">
    <tr>
        <td class="ordersTableStyle"><h3>商品编号</h3></td>
        <td class="ordersTableStyle"><h3>商品图像</h3></td>
        <td class="ordersTableStyle"><h3>价格</h3></td>
        <td class="ordersTableStyle"><h3>待付款编号</h3></td>
    </tr>
    <c:forEach items="${goodsInCartList}" var="orders" varStatus="status">
        <tr>
            <td class="ordersTableStyle">${orders.goodsId}</td>
            <td class="ordersTableStyle">
                <img src="${orders.goodsPicturePath}"  width="70" height="70">
            </td>
            <td class="ordersTableStyle">${orders.goodsPrice}</td>
            <td class="ordersTableStyle">${orders.pendingPaymentId}</td>
            <td><button id="deletGoods" onclick="del(this)">delete</button></td>
            <td><button id="pay" onclick="pay(${orders.goodsPrice},${orders.goodsId})">pay</button></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
<<script type="text/javascript">
function del(obj){
    obj.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode);
}
</script>
<script type="text/javascript">
    function pay(Price,Id){
        //var userId='${sessionScope.userId}'.toString();
        var userId="1";
        if(userId != "" ){
            var goodsId=Id.toString();
            var goodsPrice=Price.toString();
        if(!(goodsId==''||goodsPrice=='')) {
            $.ajax({
                type: 'post',
                url: 'pay',
                data: 'userId=' + userId + '&goodsId=' + goodsId + '&goodsPrice=' + goodsPrice,
                success: function (data) {
                    if (data == "PaySuccess") {
                        window.location.href = "orders?userId=" + userId;
                    } else {
                        alert("提示：付款失败[可能原因：余额不足、库存不足]");
                    }
                }
            })
        }else {
            console.error('Params exits empty,please check it!');
        }
        }else{
            alert("请先登录");
        }
    }
</script>
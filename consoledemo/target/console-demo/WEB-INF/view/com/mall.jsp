<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Demo Mall</title>
</head>
<body>
<jsp:include page="commonHeaderBanner.jsp"/>
<div align="center" class="title">
    <H2>抢购商品</H2>
</div>
<div align="center">
    <table align="center">
        <tr>
            <form  id="rushToBuyFrom" action="rushToBuyGoodsDetail">
                <img src="${rushToBuyGoods.goodsPicturePath}"  width="400" height="400">
                <input type="hidden" name="goodsId" value="${rushToBuyGoods.goodsId}">
                <table align="center">
                    <tr>
                        <td>
                            <input type="submit" class="rushToBuyBtStyle" value="商品详情">
                        </td>
                        <td>
                            <input id="rushToBuyBt" type="button" class="rushToBuyBtStyle" value="抢购" onclick="rushToBuyPay(${rushToBuyGoods.goodsId},${rushToBuyGoods.goodsPrice})">
                        </td>
                    <tr/>
                </table>
            </form >
        </tr>
    </table>
</div>
<p/>
<p/>
<div align="center" class="title">
    <H2>普通商品</H2>
</div>
<table align="center">
    <c:forEach items="${goodsList }" var="good" varStatus="status">
        <c:if test="${status.count%3==1}">
            <tr>
        </c:if>
        <td>
            <form id="buyFrom" action="normalGoodsDetail">
                <img src="${good.goodsPicturePath}"  width="200" height="200">
                <input type="hidden" name="goodsId" value="${good.goodsId}">
                <table align="center">
                    <tr>
                        <td>
                            <input type="submit" value="商品详情" class="btStyle">
                        </td>
                        <td>
                            <input id="buyBt" type="button" class="btStyle" value="购买" onclick="normalPay(${good.goodsId},${good.goodsPrice})">
                        </td>
                    <tr/>
                </table>
            </form >
        </td>
        <c:if test="${status.last}">
            </tr>
        </c:if>
        <c:if test="${status.count%3==0}">
            </tr>
        </c:if>
    </c:forEach>
</table>
<script type="text/javascript">
    function normalPay(goodsId) {
        window.location.href="payNormalGoods?goodsId="+goodsId;
    }
    function rushToBuyPay(goodsId) {
        window.location.href="payRushToBuyGoods?goodsId="+goodsId;
    }
</script>

</body>
</html>
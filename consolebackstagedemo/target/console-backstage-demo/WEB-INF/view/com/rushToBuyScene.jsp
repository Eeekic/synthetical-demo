<%--
  Created by IntelliJ IDEA.
  User: SUNPENG
  Date: 2018/8/6
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>抢购场景</title>
    <style type="text/css">
        .div-left{
            margin-left: 25px;
        }
        .bt-style{
            font-size:15px;
            width:75px;
            height: 30px;
        }
        .table-style{
            font-size:18px;
        }

        .query-input-style{
            border: 0;
            font-size:15px;
            outline:none;
            cursor: pointer;
        }

        .query-from-style{
            margin-left: 20px;
            /*display:none;*/
        }
    </style>
</head>
<script src="<%=path%>/js/jquery-3.3.1.min.js"></script>
<body>
    <h1 align="left">抢购场景压测说明</h1>
    <h2>一、多租户压测场景准备</h2>
    <div class="div-left">
        <h3 align="left">1.压测参数配置</h3>
        <div class="div-left">
            <form id="paramPresetFrom">
                <table class="table-style">
                    <tr>
                        <td>
                            <label for="rushToBuyGoodsCount">抢购商品数量</label>
                            <input id="rushToBuyGoodsCount" name="rushToBuyGoodsCount" type="text">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="rushToBuyUsersCount">抢购用户数量</label>
                            <input id="rushToBuyUsersCount" name="rushToBuyUsersCount" type="text">
                        </td>
                    </tr>
                </table>
            </form>
            <input type="button" id="paramPresetBt" value="提交" class="bt-style">
        </div>

        <h3>2.压测参数配置进度查询</h3>
        <div class="div-left">
            <input type="button" id="queryProcessBt" value="查询" class="bt-style">
            <br>
            <form class="query-from-style">
                <table class="table-style">
                    <tr>
                        <td >
                            <label for="rushToBuyGoodsCountQuery">抢购商品预置(已完成数量)：</label>
                            <input id="rushToBuyGoodsCountQuery" class="query-input-style" value="-" readonly="value">
                        </td>
                    </tr>
                    <tr>
                        <td >
                            <label for="rushToBuyUsersCountQuery">抢购用户预置(已完成数量)：</label>
                            <input id="rushToBuyUsersCountQuery" class="query-input-style" value="-" readonly="value">
                        </td>
                        <td id=""></td>
                    </tr>
                </table>
            </form>
        </div>

        <h3>3.压测参数查询</h3>
        <div class="div-left">
            <input type="button" id="queryParamBt" value="查询" class="bt-style">
            <br>
            <form class="query-from-style">
                <table class="table-style">
                    <tr>
                        <td >
                            <label for="rushToBuyGoodsId">抢购商品ID：</label>
                            <input id="rushToBuyGoodsId" class="query-input-style" value="-" readonly="value">
                        </td>
                    </tr>
                    <tr>
                        <td >
                            <label for="rushToBuyGoodsPrice">抢购商品价格(￥)：</label>
                            <input id="rushToBuyGoodsPrice" class="query-input-style" value="-" readonly="value">
                        </td>
                    </tr>
                    <tr>
                        <td >
                            <label for="rushToBuyGoodsType">抢购商品类型：</label>
                            <input id="rushToBuyGoodsType" class="query-input-style" value="-" readonly="value">
                        </td>
                    </tr>
                    <tr>
                        <td >
                            <label for="rushToBuyUsersIdRange">抢购用户ID范围：</label>
                            <input id="rushToBuyUsersIdRange" class="query-input-style" value="-" readonly="value">
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <%--<h3 align="left">4.压测数据清理</h3>--%>
        <%--<div class="div-left">--%>
            <%--<form>--%>
                <%--<input type="button" value="清理" class="bt-style">--%>
            <%--</form>--%>
        <%--</div>--%>
    </div>

    <h2>二、单租户租户调试测试</h2>

    <div class="div-left">
        <input type="button" id="singleTenantAdjustBt" value="查询" class="bt-style">
        <form class="query-from-style">
            <table>
                <tr>
                    <td>
                        <label for="rushToBuyUrlTxt">请求Body：</label>
                    </td>
                    <td>
                        <input type="text" id="rushToBuyUrlTxt" value="xxxx" class="bt-style">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="rushToBuyArea">请求Body：</label>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <textarea rows="4" cols="50" id="rushToBuyArea"></textarea>
                    </td>
                </tr>
            </table>
        </form>
    </div>

    <h2>三、多租户抢购压力测试</h2>
</body>
<script type="text/javascript">
    $('#paramPresetBt').on('click',function () {
        var goodsCount = $('#rushToBuyGoodsCount').val();
        var usersCount = $('#rushToBuyUsersCount').val();
        if(usersCount == "" || goodsCount == ""){
            alert("提示：提交信息不能为空！");
        }else{
            $.ajax({
                type:'get',
                url:'commitPresetData',
                data:$('#paramPresetFrom').serialize(),
                success:function (data) {
                    if(data=="success") {
                        alert("提示：预置任务启动成功！");
                    }else if(data=="failed"){
                        alert("提示：请核对参数输入是否正确！");
                    }else {
                        alert("提示：任务正在进行中，请勿重复提交！");
                    }
                }
            });
        }
    });

    $('#queryProcessBt').on('click',function () {
        $.ajax({
            type:'get',
            url:'testUserCount',
            data:'',
            success:function (data) {
                if(data != -1) {
                    $('#rushToBuyUsersCountQuery').attr("value",data);
                }
            }
        });
    });

    $('#queryParamBt').on('click',function () {
        $.ajax({
            type:'get',
            url:'rushToBuyGoodsDetail',
            data:'',
            success:function (data) {
                if(data != "") {
                    var goodsDetailJson = jQuery.parseJSON(data);
                    $('#rushToBuyGoodsId').attr("value",goodsDetailJson.goodsId);
                    $('#rushToBuyGoodsPrice').attr("value",goodsDetailJson.goodsPrice);
                    $('#rushToBuyGoodsType').attr("value",goodsDetailJson.goodsType);
                }else {
                    $('#rushToBuyGoodsId').attr("value","请重试...");
                }
            }
        });
        $.ajax({
            type:'get',
            url:'testUserIdRange',
            data:'',
            success:function (data) {
                if(data != "") {
                    $('#rushToBuyUsersIdRange').attr("value",data);
                }else {
                    $('#rushToBuyUsersIdRange').attr("value","请重试...");
                }
            }
        });
    });
</script>
</html>

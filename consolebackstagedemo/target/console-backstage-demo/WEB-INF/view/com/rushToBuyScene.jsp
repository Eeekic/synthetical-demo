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
    <script src="<%=path%>/js/jquery-3.3.1.min.js"></script>
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

        .rushToBuy-txt-style{
            width: 370px;
            font-size:15px;
        }
        .red {
            color:#FF0000
        }
        .front-style{
            font-size:18px;
        }
    </style>
</head>
<body>
    <h1 align="left">抢购场景压测说明</h1>
    <h2 align="left">一、抢购接口与流程框图</h2>
    <div class="div-left">
        <h3>（一）、接口说明(manager对应节点)</h3>
        <div class="div-left">
            <h4 >支付接口</h4>
            <ul><li>rest接口：<strong>v1/rest/pay</strong></li>
                <li>请求类别：post</li>
                <li>请求参数：</li>
            </ul>
            <pre class="front-style">
        {
            "userId":"1",
            "goodsId":"1",
            "goodsPrice":"2",
            "goodsType":"RushToBuy"
        }
            </pre>

            <ul><li>返回结果：</li>
            </ul>
            <pre class="front-style">
        {
            "errCode":"DbService.200",
            "resMsg":"PaySuccess"
        }
            </pre>
        </div>
        <h3>（二）、抢购流程框图</h3>
        <div class="div-left">
            <%--<img  src="<%=path %>/public/com/imgs/">--%>
            <img type="image/png" src="<%=path %>/doc/image/rush-to-buy-modle.png" width="1485" height="686">
        </div>
    </div>

    <h2 align="left">二、测试操作说明</h2>
    <div class="div-left">

        <h3>(一)、多租户压测场景准备</h3>
        <div class="div-left">
            <h4 align="left">1.压测参数配置</h4>
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

            <h4>2.压测参数配置进度查询</h4>
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

            <h4>3.压测参数查询</h4>
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

            <%--<h4 align="left">4.压测数据清理</h4>--%>
            <%--<div class="div-left">--%>
                <%--<form>--%>
                    <%--<input type="button" value="清理" class="bt-style">--%>
                <%--</form>--%>
            <%--</div>--%>
        </div>

        <h3>(二)、单租户租户调试测试</h3>
        <div class="div-left">
            <input type="button" id="singleTenantCallBt" value="发送" class="bt-style">
            <br>
            <br>
            <form class="query-from-style">
                <table class="table-style">
                    <tr>
                        <td>
                            <label for="rushToBuyUrlTxt">请求Url：</label>
                        </td>
                        <td>
                            <input type="text" id="rushToBuyUrlTxt" value="${rushToBuyUrl}" class="rushToBuy-txt-style">
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
    <textarea rows="7" cols="50" id="rushToBuyArea">{
        'userId':'1',
        'goodsId':'5',
        'goodsPrice':'2',
        'goodsType':'RushToBuy'
    }</textarea>
                        </td>
                    </tr>
                    <tr><td></td></tr>
                    <tr>
                        <td>
                            <label for="responseTimeTxt">请求响应时间(ms)：</label>
                        </td>
                        <td>
                            <input id="responseTimeTxt" class="query-input-style" value="-" readonly="value">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="rushToBuyResultArea">请求返回值：</label>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <textarea rows="7" cols="50" id="rushToBuyResultArea" disabled="disabled"></textarea>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <h3>(三)、多租户抢购压力测试</h3>
        <div class="div-left">
            <h4 align="left" class="red">多租户场景请结合华为云CPTS进行压测</h4>
        </div>
    </div>
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
        $.ajax({
            type:'get',
            url:'queryMsgCount',
            data:'',
            success: function (data){
                if(data!=-1){
                    $('#rushToBuyGoodsCountQuery').attr("value",data);
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

    $('#singleTenantCallBt').on('click',function () {
        var rushToBuyUrl = $('#rushToBuyUrlTxt').val();
        var requestBody = $('#rushToBuyArea').val().replace(/[\r\n]/g,"").replace(/[ ]/g,"");
        if(rushToBuyUrl != "" && requestBody != "") {
            $.ajax({
                type: 'post',
                url: 'pay',
                data:  'rushToBuyUrl=' + rushToBuyUrl + '&requestBody=' + requestBody,
                success: function (data) {
                    var callJson = jQuery.parseJSON(data);
                    $('#responseTimeTxt').attr("value",callJson.delay);
                    $('#rushToBuyResultArea').val(JSON.stringify(callJson.rushToBuyResult));
                }
            });
        }else {
            alert("提示：Url或者请求体不能为空！");
        }
    });
</script>
</html>

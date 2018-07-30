<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String bpath = request.getContextPath();
%>
<html>
    <body>
        <h1>综合Demo资料</h1>
        <pre>
            <%--<a href="<%=bpath %>/doc/synchetical-demo-design.html" style="font-size:26px;">DDM 数据库测模型</a>--%>
            <br>
            <a href="<%=bpath %>/doc/synchetical-demo-design.html"style="font-size:26px;">综合Demo结构与数据库设计</a>
            <br>
            <a href="<%=bpath %>/doc/synchetical-demo-api.html"style="font-size:26px;">综合Demo后台rest接口使用说明</a>
        </pre>
    </body>
</html>

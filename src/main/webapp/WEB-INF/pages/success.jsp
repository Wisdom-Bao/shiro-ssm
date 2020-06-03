<%--
  Created by IntelliJ IDEA.
  User: 智慧包
  Date: 2020/5/12
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>aaa</title>
</head>
<body>
<h1>跳转成功</h1>
${msg}<hr>
<hr>
<a href="/userMessage">查看当前用户信息</a>
<hr>

    <shiro:hasPermission name="addProduct"><a href="/add">go to add</a></shiro:hasPermission>
<shiro:hasPermission name="addOrder"><a href="/delete">go to delete</a></shiro:hasPermission>

<shiro:hasRole name="admin"><a href="/gotoaddRole">go to addRole</a></shiro:hasRole>
<%--<shiro:hasRole name="productManager"><a href="/delete">go to delete</a></shiro:hasRole>--%>

<%--    <#if shiro.hasPermission("sys:dept:save")>--%>
<%--        <a class="btn btn-primary" @click="add"><i class="fa fa-plus"></i>&nbsp;新增</a>--%>
<%--    </#if>--%>
<%--    <#if shiro.hasPermission("sys:dept:update")>--%>
<%--        <a class="btn btn-primary" @click="update"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>--%>
<%--    </#if>--%>
<%--    <#if shiro.hasPermission("sys:dept:delete")>--%>
<%--        <a class="btn btn-primary" @click="del"><i class="fa fa-trash-o"></i>&nbsp;删除</a>--%>
<%--    </#if>--%>

</body>
</html>

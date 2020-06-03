<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 智慧包
  Date: 2020/5/25
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%--<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<shiro:hasPermission name="addProduct"><a href="/add">go to add</a></shiro:hasPermission>
<shiro:hasPermission name="addOrder"><a href="/delete">go to delete</a></shiro:hasPermission>
<%--<shiro:hasRole name="admin"><a href="/add">go to add</a></shiro:hasRole>--%>
<%--<shiro:hasRole name="productManager"><a href="/delete">go to delete</a></shiro:hasRole>--%>
<h3>用户基本信息</h3>
${user}
<hr>
<h3>用户拥有的角色</h3>
${role}
<hr>
<h3>用户拥有的权限</h3>
${permisson}
</body>
</html>

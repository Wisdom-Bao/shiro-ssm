<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml" xmlns:color="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
</head>
<body>
<h1>登录</h1>
<hr>
<p th:text="${msg}"></p>
<a href="./student/findAll">查询测试</a>
dsffsdfsfdf
<form action="/login">
    用户名: <input type="text" name="username"><br>
    密码: <input type="text" name="password"><br>
    <input type="submit" value="登录">
</form>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<body>
${msg}
<form action="/login">
    用户名: <input type="text" name="username"><br>
    密码: <input type="text" name="password"><br>
    <input type="submit" value="登录">
</form>
</body>
</html>

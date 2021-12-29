<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户登录</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/user" method="post" name="login">
    <input type="hidden" name="key" value="login"/>
    用户名:&nbsp;<input type="text" id="username" name="username"><br/>
    用户密码:&nbsp;<input type="password" id="userpwd" name="userpwd"><br/>
    <input type="checkbox" id="saveusername" name="saveusername" value="ok">记住用户名<br/>
    <input type="submit" value="登录">
    <input type="reset" value="重置">&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/userReg.jsp">注册</a>
</form>
<div class="msg" style="margin-top: 20px;color:red;font-size:24px">
    ${requestScope.loginFail }
</div>
${sessionScope.loginMessage }
</body>
</html>
<!-- 记住用户名 -->
<script>
    var inputUserName = document.getElementById("username");
    var usernameValue = "${cookie.saveusername.value}";
    inputUserName.value = decodeURI(usernameValue);
</script>



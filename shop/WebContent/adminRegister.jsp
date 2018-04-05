<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${requestScope.error }</h1>
<form action = "${pageContext.request.contextPath }/AdminAction_register" method = "post">
	管理员名：<input type = "text" name = "admin.username"/><br/>
	密码：<input type = "password" name = "admin.password"/><br/>
	确认密码：<input type = "password" name = "password2"/><br/>
	手机号：<input type = "text" name = "admin.phone"/><br/>
	金额:<input type = "text" name = "admin.money"/><br/>
	<input type = "submit" value = "确认"/>
</form>
</body>
</html>
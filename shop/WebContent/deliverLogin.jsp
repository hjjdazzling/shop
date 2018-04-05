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
<form action = "${pageContext.request.contextPath }/DeliverAction_login" method = "post">
	快递：<input type = "text" name = "deliver.username"/><br/>
	密码：<input type = "password" name = "deliver.password"/><br/>
	<input type = "submit" value = "确认"/>
</form>
</body>
</html>
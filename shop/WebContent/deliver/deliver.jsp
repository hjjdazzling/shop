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
<form action = "${pageContext.request.contextPath }/DeliverAction_select" method = "post">
<table border = "1">
	<tr>
		<th>用户名字</th>
		<th>密码</th>
		<th>手机号</th>
		<th>金额</th>
	</tr>
	<tr>
		<td>${sessionScope.deliver.username }</td>
		<td><input type = "text" value = "${sessionScope.deliver.password }" name = "password"/></td>
		<td><input type = "text" value = "${sessionScope.deliver.phone }" name = "phone"/></td>
		<td>${sessionScope.deliver.money }</td>
	</tr>
</table>
<input type = "submit" value = "修改"/>
</form>
<a href = "${pageContext.request.contextPath }/deliver/select.jsp">返回上一层</a>
</body>
</html>
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
<form action = "${pageContext.request.contextPath }/ShopAction_updateShop" method = "post">
<table border = "1">
	<tr>
		<th>用户名</th>
		<th>密码</th>
		<th>手机号</th>
		<th>金额</th>
		<th>地址</th>
		<th>日期</th>
	</tr>
	<tr>
		<td>${sessionScope.shop.username }</td>
		<td><input type = "text" value = "${sessionScope.shop.password }" name = "password"/></td>
		<td><input type = "text" value = "${sessionScope.shop.phone }" name = "phone"/></td>
		<td><input type = "text" value = "${sessionScope.shop.money }" name = "money"/></td>
		<td><input type = "text" value = "${sessionScope.shop.address }" name = "address"/></td>
		<td>${sessionScope.shop.date }</td>
	</tr>
</table>
	<input type = "submit" value = "确认更新"/>
</form>
</body>
</html>
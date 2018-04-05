<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action = "${pageContext.request.contextPath }/UserAction_buyShopCar" method = "post">
<table border = "1">
	<tr>
		<th>商品名字</th>
		<th>商品功能</th>
		<th>商品价格</th>
		<th>数量</th>
		<th>选择</th>
	</tr>
	<c:if test="${sessionScope.shopCar != null }">
	<c:forEach var="good" items="${sessionScope.shopCar }">
		<tr>
			<td>${good.key.name}</td>
			<td>${good.key.function}</td>
			<td>${good.key.price}</td>
			<td>${good.value }</td>
			<td><input type="checkbox" name="buyGood" value="${good.key.id }" /></td>
		</tr>
	</c:forEach>
	</c:if>
</table>
<input type = "submit" name = "确认">
<a href = "${pageContext.request.contextPath }/user/select.jsp">返回选择页面</a>
</form>
</body>
</html>
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
<h1>${requestScope.error }</h1>
<table border = "1">
	<tr>
		<td>商品id</td>
		<td>商品名称</td>
		<td>功能</td>
		<td>价格</td>
 	</tr>
 	<c:if test="${sessionScope.good != null }">
	<c:forEach var = "record" items="${sessionScope.good }">
		<tr>
			<td>${record.id }</td>
			<td>${record.name }</td>
			<td>${record.function }</td>
			<td>${record.price }</td>
		</tr>
	</c:forEach>	
	</c:if>
</table>
<form action = "${pageContext.request.contextPath }/UserAction_insertShopCar" method = "post">
	商品id:<input type = "text" name = "id"/><br/>
	商品数量:<input type = "text" name = "number"/><br/>
	<input type = "submit" value = "加入购物车"/>
</form>
</body>
</html>
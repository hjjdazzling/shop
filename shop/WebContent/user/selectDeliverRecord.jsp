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
		<th>商品id</th>
		<th>商品名字</th>
		<th>商品价格</th>
		<th>状态</th>
		<th>目前所在地址</th>
	</tr>
	<c:if test="${requestScope.deliverRecord != null }">
	<c:forEach var = "record" items="${requestScope.deliverRecord }">
		<tr>
			<td>${record.purchaseRecord.id }</td>
			<td>${record.purchaseRecord.good.name }</td>
			<td>${record.purchaseRecord.good.price }</td>
			<td>${record.purchaseRecord.number }</td>
			<td>${record.type }</td>
			<td>${record.address }</td>
		</tr>
	</c:forEach>	
	</c:if>
</table>
<a href = "${pageContext.request.contextPath }/user/select.jsp">返回选择页面</a>
<form action = "${pageContext.request.contextPath }/UserAction_confirmGood" method = "post">
	确认收货id:<input type = "text" name = "id"/>
	<input type = "submit" value = "确认"/>
</form>
</body>
</html>
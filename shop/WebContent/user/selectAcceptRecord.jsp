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
		<th>商品数量</th>
		<th>状态</th>
	</tr>
	<c:if test="${sessionScope.acceptRecord != null }">
	
	<c:forEach var = "record" items="${sessionScope.acceptRecord }">
		<tr>
			<td>${record.id }</td>
			<td>${record.good.name }</td>
			<td>${record.good.price }</td>
			<td>${record.number }</td>
			<td>${record.situation }</td>
		</tr>
	</c:forEach>	
	</c:if>
</table>
<form action = "${pageContext.request.contextPath }/UserAction_backGood" method = "post">
	请输入退货id:<input type = "text" name = "id"/>
	<input type = "submit" value = "确认"/>
</form>
<a href = "${pageContext.request.contextPath }/user/select.jsp">返回选择页面</a>
</body>
</html>
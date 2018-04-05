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
		<th>功能</th>
		<th>价格</th>
		
	</tr>
	<c:if test="${sessionScope.good != null }">
		<c:forEach var = "record" items = "${sessionScope.good }">
			<tr>
				<td>${record.id }</td>
				<td>${record.name }</td>
				<td>${record.function }</td>
				<td>${record.price }</td>
			</tr>
		</c:forEach>
	</c:if>
</table>
<hr/>
<h1 align="center">修改商品信息</h1>
<form action = "${pageContext.request.contextPath }/ShopAction_updateGood" method = "post"> 
	id:<input type = "text" name = "id"/><br/>
	名字:<input type = "text" name = "name"/><br/>
	功能描述:<input type = "text" name = "function"/><br/>
	价格:<input type = "text" name = "price"/><br/>
	<input type = "submit" value = "确认更改"/>
</form>
<a href = "${pageContext.request.contextPath }/shop/select.jsp">返回上一层</a>
</body>
</html>
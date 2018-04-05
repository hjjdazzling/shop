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
<form action = "${pageContext.request.contextPath }/DeliverAction_updateDeliverRecord" method = "post">
<table border = "1">
	<tr>
		<th>送货id</th>
		<th>购物id</th>
		<th>状态</th>
		<th>目前地址</th>
		<th>收货地址</th>
	</tr>
	<c:if test="${sessionScope.deliverRecord != null }">
		<c:forEach var = "record" items = "${sessionScope.deliverRecord }">
			<tr>
			<td>${record.id }</td>
			<td>${record.purchaseRecord.id }</td>
			<td><input type = "text" value = "${record.type }" name = "type"/></td>
			<td><input type = "text" value = "${record.address }" name = "address"/> </td>
			<td>${record.targetAddress }</td>
			</tr>
		</c:forEach>
	</c:if>
</table>
注释:只能选择(送货，确认收货，等待确认收货，退货，等待确认退货, 确认退货)<br/>
要更新的送货id:<input type = "text" name = "id"/><br/>
<input type = "submit" value = "确认更新"/>
</form>
</body>
</html>
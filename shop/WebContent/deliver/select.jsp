<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${requestScope.message }</h1>
<a href = "${pageContext.request.contextPath }/deliver/deliver.jsp">查看个人信息(修改)</a>
<a href = "${pageContext.request.contextPath }/DeliverAction_selectDeliverRecord">查看订单并更新记录</a>
</body>
</html>
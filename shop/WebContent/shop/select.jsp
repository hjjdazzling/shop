<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<h1>${requestScope.message }</h1>
<body>
<a href = "${pageContext.request.contextPath }/shop/shop.jsp">查看个人信息(修改)</a>
<a href = "${pageContext.request.contextPath }/ShopAction_selectGood">查看自己的商品</a>
<a href = "${pageContext.request.contextPath }/shop/insertGood.jsp">上架新的商品</a>
</body>
</html>
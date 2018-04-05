<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action = "${pageContext.request.contextPath }/ShopAction_insertGood" method = "post">
	商品名字:<input type = "text" name = "good.name"/><br/>
	商品描述:<input type = "text" name = "good.function"/><br/>
	商品价格:<input type = "text" name = "good.price"/><br/>
	<br/><input type = "submit" name = "确认"/> 
</form>
</body>
</html>
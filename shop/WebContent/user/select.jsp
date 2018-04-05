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
<a href = "${pageContext.request.contextPath }/UserAction_selectGoods">查看商品</a>
<a href = "${pageContext.request.contextPath }/UserAction_selectShopCar">查看购物车</a>
<a href = "${pageContext.request.contextPath }/UserAction_selectPurchaseRecord">查看购物记录</a>
<a href = "${pageContext.request.contextPath }/UserAction_selectAcceptRecord">查看收货记录</a>
<a href = "${pageContext.request.contextPath }/UserAction_selectDeliverRecord">查看送货记录</a>
<a href = "${pageContext.request.contextPath }/UserAction_selectReturnRecord">查看退货记录</a>
<a href = "${pageContext.request.contextPath }/UserAction_personData">查看用户信息(充值)</a>
<a href = "${pageContext.request.contextPath }/UserAction_quit">退出</a>
</body>
</html>
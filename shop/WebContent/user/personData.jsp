<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${requestScope.error }</h1>
<form action = "${pageContext.request.contextPath }/UserAction_updateData"　method = "post">
<input type = "hidden" value = "${sessionScope.user.id }" name = "user.id"/>
<input type = "hidden" value = "${sessionScope.user.date }" name = "user.date"/>
<input type = "hidden" value = "${sessionScope.user.username }" name = "user.username"/>
<table border = "1">
	<tr>
		<td>用户名</td>
		<td>密码</td>
		<td>手机号</td>
		<td>地址</td>
		<td>注册日期</td>
		<td>余额</td>
	</tr>
	<tr>
		<td>
			${sessionScope.user.username }
		</td>
		<td>
			<input type = "text" value = "${sessionScope.user.password }" name = "user.password"/>
		</td>
		<td>
			<input type = "text" value = "${sessionScope.user.phone }" name = "user.phone"/>
		</td>
		<td>
			<input type = "text" value = "${sessionScope.user.address }" name = "user.address"/>
		</td>
		<td>
			${sessionScope.user.date }
		</td>
		<td>
			<input type = "text" value = "${sessionScope.user.money }" name = "user.money"/>
		</td>
	</tr>
</table>
<br/>
<input type = "submit" value = "修改资料"/>
</form>
<br/>
<a href = "${pageContext.request.contextPath }/user/select.jsp">返回选择页面</a>
</body>
</html>
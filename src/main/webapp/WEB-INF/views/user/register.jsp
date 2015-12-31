<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<title>个人信息</title>
</head>
<body>
	<div class="register_container">
		<form action="/user/doRegister" method="post">
			用户名：<input type="text" name="username">
			密码：<input type="password" name="password">
			邮箱：<input type="text" name="email">
			<input type="submit" value="注册">
		</form>
	</div>
	<div>${isOk}</div></div>
</body>
</html>
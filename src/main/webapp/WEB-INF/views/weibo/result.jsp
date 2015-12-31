<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新浪登录</title>
</head>
<body>
	微博昵称:${user.screenName}
	</br> 用户姓名:${user.name}
	</br> 头像:
	<img alt="用户头像" src="${user.profileImageUrl}">
	<br>

</body>
</html>
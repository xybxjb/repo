<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath + "/"%>"></base>
</head>
<body>

	<div class="jumbotron">
	
			<h1 align="center" style="color: red">对不起，您没有权限访问此模块</h1>

		<img src="static/images/404.jpg" width="50%" height="30%"
			style="margin-left: 25%; float: left;">

	</div>
</body>
</html>

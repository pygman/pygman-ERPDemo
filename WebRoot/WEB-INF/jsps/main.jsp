<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/index.css" rel="stylesheet" type="text/css" />
<title>系统主页</title>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
</head>
<body>
	欢迎您:${sessionScope.loginEm['name']}
	<a href="page_emp_changePwd.action" target="main">修改密码</a>
	<a href="emp_logout.action">退出登录</a>
	<br />
	<div
		style="width: 164px; height: 509px; margin: overflow:hidden; float: left;">
		<img src="images/left-top.gif" width="162" height="25" />
		<%@include file="/WEB-INF/jsps/tools/menu.jsp"%>
	</div>
	<iframe id="frame-contect" src="pages_context.action"
		style="width: 848px; float: right; height: 530px" scrolling="no"
		name="main" frameborder="0"></iframe>
	<%@include file="/WEB-INF/jsps/tools/mask.jsp"%>
</body>
</html>

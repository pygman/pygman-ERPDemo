<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<title>系统登录页</title>
<script>
	$(function() {
		$("#login_ok").click(function() {
			$("form:first").submit();
		});
	});
	function MM_swapImage(srcObj, image_src) {
		srcObj.src = image_src;
	}
</script>
</head>
<body>
	<s:form action="emp_login" method="post">
		用户名:<s:textfield name="em.userName" value="admin" />
		<br />
		密码：<s:textfield name="em.pwd" value="admin" />
		<br />
		验证码:<input type="text" size="9" />
		<img src="images/test.gif" />
		<br />
		<a href="javascript:void(0)" id="login_ok"> <img
			src="images/denglu_bg_03.gif" name="Image1" width="40" height="22"
			border="0" onmouseover="MM_swapImage(this,'images/denglu_h_03.gif')"
			onmouseout="MM_swapImage(this,'images/denglu_bg_03.gif')" />
		</a>
		<a href="#"> <img src="images/giveup_bg_03.gif" name="Image2"
			width="42" height="22" border="0"
			onmouseover="MM_swapImage(this,'images/giveup_h_03.gif')"
			onmouseout="MM_swapImage(this,'images/giveup_bg_03.gif')" />
		</a>
	</s:form>
</body>
</html>

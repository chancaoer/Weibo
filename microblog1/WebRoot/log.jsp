<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>微博——登录</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link href="css/first.css" rel="stylesheet" type="text/css"
			media="screen" />

		<script type="text/javascript">
var xmlHttp = null;
function getXmlHttp() {
	try {
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	} catch (e) {
		xmlHttp = null;
	}
	return xmlHttp;
}

function checknull() {
	xmlHttp = getXmlHttp();
	if (xmlHttp == null) {
		alert("亲，你的浏览器不支持AJAX");
		return;
	} else {
		if (document.frmlogin.email.value == "") {
			alert("请输入登陆邮箱！ ");
		}
		if (document.frmlogin.password.value == "") {
			alert("请输入登陆密码！")
		}

	}
}
</script>
	</head>

	<body>

		<div id="wrapper">
			<div id="header-wrapper">
				<div id="header">
					<div id="logo">
					<!-- 	<img alt="" src="images/logo.jpg" width="100px" height="100px"> -->
					</div>

				</div>
			</div>
			<!-- end #header -->

		
			<div id="page">
				<div id="page-bgtop">
					<div id="page-bgbtm">
						<div id="content">

							<table width="90%">
								<s:set var="userListIndex" value="-1" />
								<s:iterator value="#session.msgInfosTop">
									<s:set var="userListIndex" value="#userListIndex+1" />
									<tr>
										<td rowspan="2" width="70">
											<img
												src="head/<s:property value="#session.usersMsgList.get(#userListIndex).faceing"/>"
												height="60" width="60">
										</td>
										<td>
											<s:property
												value="#session.usersMsgList.get(#userListIndex).name" />
											:
											<font size="2"> <s:property value="content" /> </font>
										</td>
										<td>
										</td>
									</tr>
									<tr>
										<td>
											<font size="2"><s:property value="r_time" /> </font>
										</td>
										<td>

										</td>
									</tr>
									<tr>
										<td colspan="3">
											<hr size="1" />
										</td>
									</tr>
								</s:iterator>
							</table>

							<div style="clear: both;">
								&nbsp;
								<a href="register.jsp"> <img alt="" src="images/register.jpg"
									border="0"> </a>
							</div>
							
						</div>
						<!-- end #content -->
						<div id="sidebar">
							
							<form action="/microblog/userlogin.action" method="post"
								name="frmlogin">
								登录邮箱：
								<input type="text" name="email" size="20">
								<br>
								<br>
								登录密码：
								<input type="password" name="password" size="21">
								<br>
								<br>
								<input type="image" src="images/login.jpg" onclick="checknull()" />
							</form>
						</div>
						<!-- end #sidebar -->
						<div style="clear: both;">
							&nbsp;
						</div>
					</div>
				</div>
			</div>
			<!-- end #page -->
		</div>
		<div id="footer-wrapper">
			<div id="footer">
				<p>
					Copyright (c) 2008 Sitename.com. All rights reserved. Design by
					<a href="#">zhongbei.weibo.com</a>.
				</p>
			</div>
			<!-- end #footer -->
		</div>
	</body>
</html>

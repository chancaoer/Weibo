<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>微博——找人</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" type="text/css" href="css/second.css">

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

	function changeShouting(follow_id, user_id) {
		xmlHttp = getXmlHttp();
		if (xmlHttp == null) {
			alert("亲，你的浏览器不支持AJAX");
			return;
		} else {
			var url = "/microblog/reladd.action?follow_id=" + follow_id
					+ "&user_id=" + user_id;
			xmlHttp.open("post", url, true);
			xmlHttp.send(null);
			xmlHttp.onreadystatechange = stateChanged(follow_id);

		}
	}

	function stateChanged(listenImgId) {
		var alreadyListen = document.getElementById("alreadyListen_"
				+ listenImgId);
		var listenImg = document.getElementById("shouting_" + listenImgId);
		listenImg.style.display = "none";
		alreadyListen.style.display = "";

		var listenNum = document.getElementById("listenNum").innerHTML;
		listenNum = parseInt(listenNum) + 1;
		document.getElementById("listenNum").innerHTML = listenNum;
	}
</script>

	</head>

	<body>
		<div id="wrapper">
			
	
			<div id="page">
				<div id="page-bgtop">
					<div id="page-bgbtm">
						<div id="content">


							<table width="90%">
								<s:iterator value="#session.usersAllB">
									<tr>
										<td>
											<img src="head/<s:property value="faceing" />" height="50"
												width="50">
										</td>
										<td>
											<s:property value="name" />
											(
											<s:property value="email" />
											)

											<!--其他用户的id -->
											<input type="hidden" value="<s:property value="id" />"
												name="follow_id">

											<!-- 当前登陆用户的id -->
											<input type="hidden"
												value="<s:property value="#session.user.id" />"
												name="user_id">


										</td>
										<td>
											<input type="image" id="shouting_<s:property value="id"/>"
												src="images/shouting.jpg"
												onclick="changeShouting('<s:property value="id"/>','<s:property value="#session.user.id" />')">

											<span id="alreadyListen_<s:property value="id"/>"
												style="display: none;"> <img alt=""
													src="images/yishouting.jpg"> <a
												href="javascript:void(0)">取消</a> </span>
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
							</div>
						</div>
						<!-- end #content -->
						<div id="sidebar">
							<table>
								<tr>
									<td>
										<img src="head/<s:property value='#session.user.faceing' />"
											height="100" width="100">
									</td>
									<td>
										<s:property value="#session.user.name" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										听众：
										<s:property value="#session.userInfoCount.fans_count" />
										收听：
										<span id="listenNum"> <s:property
												value="#session.userInfoCount.follow_count" /> </span> 广播：
										<s:property value="#session.userInfoCount.msg_count" />
									</td>
								</tr>
							</table>
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
					<a href="#">XXXXXXXXXXXXXXXXXXXX</a>.
				</p>
			</div>
			<!-- end #footer -->
		</div>
	</body>
</html>

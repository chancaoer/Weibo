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

		<title>微博——首页</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" type="text/css" href="css/second.css">

		<SCRIPT type="text/javascript">
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

	function openZhuanBo(msgInfos_id) {
		xmlHttp = getXmlHttp();
		if (xmlHttp == null) {
			alert("亲，你的浏览器不支持AJAX");
			return;
		} else {
			var tabZhuanBo = document.getElementById("tabZhuanBo_"+msgInfos_id);

			tabZhuanBo.style.display= "";

		}
	}
	
		function openPingLun(msgInfosping_id) {
		xmlHttp = getXmlHttp();
		if (xmlHttp == null) {
			alert("亲，你的浏览器不支持AJAX");
			return;
		} else {
			var tabPingLun = document.getElementById("tabPingLun_"+msgInfosping_id);

			tabPingLun.style.display= "";

		}
	}
	
	function closed(msgInfos_id){
	var tabZhuanBo=document.getElementById("tabZhuanBo_"+msgInfos_id);
	tabZhuanBo.style.display="none";
	return;
	}
	
	function closedping(msgInfosping_id){
	var tabPingLun=document.getElementById("tabPingLun_"+msgInfosping_id);
	tabPingLun.style.display="none";
	return;
	}
	
	
</SCRIPT>
	</head>

	<body>



		<div id="wrapper">
			<div id="page">
				<div id="page-bgtop">
					<div id="page-bgbtm">
						<div id="content">
							<div class="post">
								<form action="/microblog/msgadd.action" method="post">
									<table>
										<tr>
											<td>
												<textarea rows="4" cols="70" name="content" id="msg"></textarea>
											</td>
										</tr>
										<tr>
											<td align="right">
												<input type="hidden"
													value="<s:property value='#session.user.id' />"
													name="user_id">
												<input type="image" src="images/guangbo.png">
											</td>
										</tr>
									</table>
								</form>
							</div>
							<table width="90%">
								<s:set var="userListIndex" value="-1" />
								<s:iterator value="#session.msgInfosLogin">
									<s:set var="userListIndex" value="#userListIndex+1" />
									<tr>
										<td rowspan="2" width="70">
											<img
												src="head/<s:property value="#session.userList.get(#userListIndex).faceing"/>"
												height="60" width="60">
										</td>
										<td>
											<s:property
												value="#session.userList.get(#userListIndex).name" />
											:
											<font size="2"> <s:if test="content.contains('#!@')">
													<s:property value="content.split('#!@')[0]" />
													<div
														style="border: solid 1px #ccc; padding: 8; margin-left: 15;">
														<s:property value="content.split('#!@')[1]" />
														<br />
														<s:property value="content.split('#!@')[2]" />
													</div>
												</s:if> <s:else>
													<s:property value="content" />
												</s:else> </font>
										</td>
										<td>
										</td>
									</tr>
									<tr>
										<td>
											<font size="2"><s:property value="r_time" /> </font>
										</td>
										<td align="right">
											<font size="-1"><a href="javascript:void(0)"
												onclick="openZhuanBo('<s:property value="id" />')"
												id="infos_<s:property value='id' />"
												style="text-decoration: none">转播</a>&nbsp;<a
												href="javascript:void(0)"
												onclick="openPingLun('<s:property value="id" />')"
												id="infosp_<s:property value='id' />"
												style="text-decoration: none">评论</a>&nbsp; <s:if
													test="#session.user.id==#session.userList.get(#userListIndex).id">
													<a
														href="/microblog/msgdeleteMsgInfo.action?id=<s:property value="id" />"
														name="id" style="text-decoration: none">删除</a>
												</s:if> <s:else>
													<a href="javascript:void(0)" style="text-decoration: none">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
												</s:else> </font>
										</td>
									</tr>
									<tr>
										<td></td>
										<td colspan="2">
											<div style="border: solid 1px #ccc; display: none;"
												id="tabZhuanBo_<s:property value='id' />">
												<form action="/microblog/msgsaveZhuanBo.action"
													method="post">
													<table>
														<tr>
															<td>
																<font size="-1">转播原文，把它分享给你的听众</font>
															</td>
															<td align="right">
																<img src="images/close.jpg"
																	onclick="closed(<s:property value='id' />)">
															</td>
														</tr>
														<tr>
															<td colspan="2">
																<font size="-1">顺便说两句:</font>
															</td>
														</tr>
														<tr>
															<td colspan="2">
																<textarea rows="3" cols="60" name="msg_content"
																	id="msg_content"></textarea>
															</td>
														</tr>
														<tr>
															<td align="right" colspan="2">
																<input type="hidden"
																	value="<s:property value='#session.user.id' />"
																	name="zhuanboid">

																<input type="hidden"
																	value="<s:property value='#session.userList.get(#userListIndex).id' />"
																	name="author_id">

																<input type="hidden" value="<s:property value="id" />"
																	name="yuanmsgid">

																<input type="image" src="images/zhuanbo.jpg">
															</td>
														</tr>
													</table>
												</form>
											</div>
											<div style="border: solid 1px #ccc; display: none;"
												id="tabPingLun_<s:property value='id' />">
												<form action="/microblog/msgsavePingLun.action"
													method="post">
													<table>
														<tr>
															<td>
																<font size="-1">评论原文：</font>
															</td>
															<td align="right">
																<img src="images/close.jpg"
																	onclick="closedping(<s:property value='id' />)">
															</td>
														</tr>
														<tr>
															<td colspan="2">
															</td>
														</tr>
														<tr>
															<td colspan="2">
																<textarea rows="3" cols="60" name="msgping_content"
																	id="msgping_content"></textarea>
															</td>
														</tr>
														<tr>
															<td align="right" colspan="2">
																<input type="hidden"
																	value="<s:property value='#session.user.id' />"
																	name="pinglunid">

																<input type="hidden"
																	value="<s:property value='#session.userList.get(#userListIndex).id' />"
																	name="authorping_id">

																<input type="hidden" value="<s:property value="id" />"
																	name="yuanmsgpingid">

																<input type="image" src="images/pinglun.jpg">
															</td>
														</tr>
													</table>
												</form>
											</div>
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
										<s:property value="#session.userInfoCount.follow_count" />
										广播：
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

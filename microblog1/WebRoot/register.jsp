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

		<title>微博——注册</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="css/first.css" rel="stylesheet" type="text/css"
			media="screen" />

		<script type="text/javascript" src="js/register.jsp">
	
</script>

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

	//验证电子邮箱
	function checkEmail(emailvalue) {
		xmlHttp = getXmlHttp();
		if (xmlHttp == null) {
			alert("亲，你的浏览器不支持AJAX");
			return;
		} else {
			var email = document.getElementById("email");

			var emailspan = document.getElementById("emailspan");
			var emailtrue = document.getElementById("emailtrue");
			var emailfalse = document.getElementById("emailfalse");

			//对电子邮件的验证
			var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			if (email.value == "") {
				emailtrue.style.display = "none";
				emailfalse.style.display = "";
				emailspan.innerHTML = "<font size='2' color='#E56C0A'>"+ "电子邮箱不能为空！"
						+"</font>";

			} else if (email.value != "") {
				if (!myreg.test(email.value)) {
					emailtrue.style.display = "none";
					emailfalse.style.display = "";
					emailspan.innerHTML = "<font size='2' color='#E56C0A'>" + "电子邮箱格式不正确！"
							+"</font>";
				} else {
					emailtrue.style.display = "";
					emailfalse.style.display = "none";
					emailspan.innerHTML = "";
				}
			}
		}
	}

	//验证姓名
	function checkName() {
		xmlHttp = getXmlHttp();
		if (xmlHttp == null) {
			alert("亲，你的浏览器不支持AJAX");
			return;
		} else {
			var name = document.getElementById("name");

			var namespan = document.getElementById("namespan");
			var nametrue = document.getElementById("nametrue");
			var namefalse = document.getElementById("namefalse");
			if (name.value == "") {
				nametrue.style.display = "none";
				namefalse.style.display = "";
				namespan.innerHTML = "<font size='2' color='#E56C0A'>" + "姓名不能为空！"
						+ "</font>";

			} else if (name.value != "") {
				namefalse.style.display = "none";
				namespan.innerHTML = "";
			}
		}
	}

	//验证密码
	function checkPassword() {
		xmlHttp = getXmlHttp();
		if (xmlHttp == null) {
			alert("亲，你的浏览器不支持AJAX");
			return;
		} else {
			var password = document.getElementById("password");

			var passwordspan = document.getElementById("passwordspan");
			var passwordtrue = document.getElementById("passwordtrue");
			var passwordfalse = document.getElementById("passwordfalse");
			if (password.value == "") {
				passwordtrue.style.display = "none";
				passwordfalse.style.display = "";
				passwordspan.innerHTML = "<font size='2' color='#E56C0A'>" + "密码不能为空！"
						+ "</font>";

			} else if (password.value != "") {
				passwordtrue.style.display = "";
				passwordfalse.style.display = "none";
				passwordspan.innerHTML = "";
			}
		}
	}

	//验证确认密码
	function checkPwd() {
		xmlHttp = getXmlHttp();
		if (xmlHttp == null) {
			alert("亲，你的浏览器不支持AJAX");
			return;
		} else {
			var pwd = document.getElementById("pwd");

			var pwdspan = document.getElementById("pwdspan");
			var pwdtrue = document.getElementById("pwdtrue");
			var pwdfalse = document.getElementById("pwdfalse");

			var password = document.getElementById("password");
			if (pwd.value == "") {
				pwdtrue.style.display = "none";
				pwdfalse.style.display = "";
				pwdspan.innerHTML = "<font size='2' color='#E56C0A'>" + "密码不能为空！"
						+ "</font>";

			} else if (pwd.value != "") {
				if (pwd.value != password.value) {
					pwdtrue.style.display = "none";
					pwdfalse.style.display = "";
					pwdspan.innerHTML = "<font size='2' color='#E56C0A'>" + "两次输入的密码不同！"
							+ "</font>";
				} else {
					pwdtrue.style.display = "";
					pwdfalse.style.display = "none";
					pwdspan.innerHTML = "";
				}
			}

		}
	}

	//Ajax验证码
	function changeImage() {
		var url = "ImageCodeServlet";
		url = convertURL(url);

		document.getElementById("imagecode").src = url;

	}
	function convertURL(url) {
		var timetamp = (new Date()).valueOf();
		url = url + "?t=" + timetamp;
		return url;
	}

	//验证验证码
	function checkCode(url) {
		xmlHttp = getXmlHttp();
		if (xmlHttp == null) {
			alert("亲，你的浏览器不支持AJAX");
			return;
		} else {

			var code = document.getElementById("code");

			var codespan = document.getElementById("codespan");
			var codetrue = document.getElementById("codetrue");
			var codefalse = document.getElementById("codefalse");

			if (code.value == "") {
				codetrue.style.display = "none";
				codefalse.style.display = "";
				codespan.innerHTML = "<font size='2' color='#E56C0A'>" + "验证码不能为空！"
						+ "</font>";
			} else if (code.value != "") {
				codetrue.style.display = "";
				codefalse.style.display = "none";

				var parameter = "code=" + code;
				sendAsynchronRequest(url, parameter, checkCodeCallback);
			}
		}
	}

	//检查验证码回调函数
	function checkCodeCallback() {
		var codetrue = document.getElementById("codetrue");
		var codefalse = document.getElementById("codefalse");

		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var xmlDoc = xmlHttp.responseXML;
				//下面是解析xml并更新页面内容

				//获得xml中元素节点message
				var message = xmlDoc.getElementsByTagName("message")[0];
				//获得xml中元素节点error
				var error = xmlDoc.getElementsByTagName("error")[0];
				//从html中获得元素节点registerDiv
				var codespan = document.getElementById("codespan");
				if (message != null) {
					codetrue.style.display = "";
					codefalse.style.display = "none";
					codespan.innerHTML = "";
					codespan.innerHTML = "<font size='2' color='#E56C0A'>"
							+ message.firstChild.nodeValue + "</font>";
				}
				if (error != null) {
					codetrue.style.display = "none";
					codefalse.style.display = "";
					codespan.innerHTML = "<font size='2' color='#E56C0A'>"
							+ error.firstChild.nodeValue + "</font>";
				}
			} else {
				alert("\u8bf7\u6c42\u7684\u9875\u9762\u6709\u5f02\u5e38");
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
						<!--  <img alt="" src="images/logo.jpg" width="100px" height="100px">-->
					</div>

				</div>
			</div>

			<div id="page">
				<div id="page-bgtop">
					<div id="page-bgbtm">
						<div id="content">
							<form action="/microblog/useradd.action" method="post"
								name="frmregister">
								<table>
									<tr>
										<td align="right">
											<span style="color: #FF0000;">*</span>电子邮箱：
										</td>
										<td>
											<input type="text" size="26" name="email"
												onblur="checkEmail(this);">

											<span id="emailtrue" style="display: none;"> </span>
											<span id="emailfalse" style="display: none;"><img
													src="images/falsecha.png" align="bottom" /> </span>
											<span id="emailspan"></span>

										</td>
									</tr>
									<tr>
										<td align="right">
											<span style="color: #FF0000;">*</span>姓名：
										</td>
										<td>
											<input type="text" size="26" name="name"
												onblur="checkName();">

											<span id="nametrue" style="display: none;"> </span>
											<span id="namefalse" style="display: none;"><img
													src="images/falsecha.png" align="bottom" /> </span>
											<span id="namespan"></span>
										</td>
									</tr>
									<tr>
										<td align="right">
											<span style="color: #FF0000;">*</span>密码：
										</td>
										<td>
											<input type="password" size="28" name="password"
												onblur="checkPassword();">

											<span id="passwordtrue" style="display: none;"> </span>
											<span id="passwordfalse" style="display: none;"><img
													src="images/falsecha.png" align="bottom" /> </span>
											<span id="passwordspan"></span>
										</td>
									</tr>
									<tr>
										<td align="right">
											<span style="color: #FF0000;">*</span>确认密码：
										</td>
										<td>
											<input type="password" size="28" name="pwd"
												onblur="checkPwd();">

											<span id="pwdtrue" style="display: none;"> </span>
											<span id="pwdfalse" style="display: none;"><img
													src="images/falsecha.png" align="bottom" /> </span>
											<span id="pwdspan"></span>
										</td>
									</tr>
									<tr>
										<td align="right">
											<span style="color: #FF0000;">*</span>生日：
										</td>
										<td>
											<select name="year">
												<option value="2009">
													2009
												</option>
												<option value="2008">
													2008
												</option>
												<option value="2007">
													2006
												</option>
												<option value="2005">
													2005
												</option>
												<option value="2004">
													2004
												</option>
												<option value="2003">
													2003
												</option>
												<option value="2002">
													2002
												</option>
												<option value="2001">
													2001
												</option>
												<option value="2000">
													2000
												</option>
												<option value="1999">
													1999
												</option>
												<option value="1998">
													1998
												</option>
												<option value="1997">
													1997
												</option>
												<option value="1996">
													1996
												</option>
												<option value="1995">
													1995
												</option>
												<option value="1994">
													1994
												</option>
												<option value="1993">
													1993
												</option>
												<option value="1992">
													1992
												</option>
												<option value="1991">
													1991
												</option>
												<option value="1990">
													1990
												</option>
											</select>
											年
											<select name="month">
												<option value="1">
													1
												</option>
												<option value="2">
													2
												</option>
												<option value="3">
													3
												</option>
												<option value="4">
													4
												</option>
												<option value="5">
													5
												</option>
												<option value="6">
													6
												</option>
												<option value="7">
													7
												</option>
												<option value="8">
													8
												</option>
												<option value="9">
													9
												</option>
												<option value="10">
													10
												</option>
												<option value="11">
													11
												</option>
												<option value="12">
													12
												</option>
											</select>
											月
											<select name="day">
												<option value="1">
													1
												</option>
												<option value="2">
													2
												</option>
												<option value="3">
													3
												</option>
												<option value="4">
													4
												</option>
												<option value="5">
													5
												</option>
												<option value="6">
													6
												</option>
												<option value="7">
													7
												</option>
												<option value="8">
													8
												</option>
												<option value="9">
													9
												</option>
												<option value="10">
													10
												</option>
												<option value="11">
													11
												</option>
												<option value="12">
													12
												</option>
												<option value="13">
													13
												</option>
												<option value="14">
													14
												</option>
												<option value="15">
													15
												</option>
												<option value="16">
													16
												</option>
												<option value="17">
													17
												</option>
												<option value="18">
													18
												</option>
												<option value="19">
													19
												</option>
												<option value="20">
													20
												</option>
												<option value="21">
													21
												</option>
												<option value="22">
													22
												</option>
												<option value="23">
													23
												</option>
												<option value="24">
													24
												</option>
												<option value="25">
													25
												</option>
												<option value="26">
													26
												</option>
												<option value="27">
													27
												</option>
												<option value="28">
													28
												</option>
												<option value="29">
													29
												</option>
												<option value="30">
													30
												</option>
												<option value="31">
													31
												</option>
											</select>
											日
										</td>
									</tr>
									<tr>
										<td align="right">
											<span style="color: #FF0000;">*</span>性别：
										</td>
										<td>
											<input type="radio" name="sex" value="男" checked="checked">
											男
											<input type="radio" name="sex" value="女">
											女
										</td>
									</tr>
									<tr>
										<td align="right">
											电话：
										</td>
										<td>
											<input type="text" size="26" name="phone">
										</td>
										<td></td>
									</tr>
									<tr>
										<td align="right">
											<span style="color: #FF0000;">*</span>头像：
										</td>
										<td>
											<select name="faceing"
												onchange="document.getElementById('img_face').src='head/'+this.value">
												<option value="head1.jpg">
													head1.jpg
												</option>
												<option value="head2.jpg">
													head2.jpg
												</option>
												<option value="head3.jpg">
													head3.jpg
												</option>
												<option value="head4.jpg">
													head4.jpg
												</option>
												<option value="head5.jpg">
													head5.jpg
												</option>
												<option value="head6.jpg">
													head6.jpg
												</option>
												<option value="head7.jpg">
													head7.jpg
												</option>
												<option value="head8.jpg">
													head8.jpg
												</option>
												<option value="head9.jpg">
													head9.jpg
												</option>
												<option value="head10.jpg">
													head10.jpg
												</option>
											</select>
											<img src="head/head1.jpg" width="80" height="80"
												id="img_face">
										</td>
									</tr>
									<tr>
										<td align="right">
											<span style="color: #FF0000;">*</span>验证码：
										</td>
										<td>
											<input type="text" size="26" name="code"
												onblur="checkCode('CheckCodeServlet');">

											<span id="codetrue" style="display: none;"> </span>
											<span id="codefalse" style="display: none;"><img
													src="images/falsecha.png" align="bottom" /> </span>
											<span id="codespan"></span>
										</td>
									</tr>
									<tr>
										<td>
										</td>
										<td>
											输入下图字符，不区分大小写
											<br />
											<img height="53" width="130" src="ImageCodeServlet"
												name="imagecode" id="imagecode" />

											<a href="javascript:changeImage();">看不清，换一张</a>
										</td>
									</tr>
									<tr>
										<td colspan="2" align="center">
											<input type="submit" value="确定并同意以下条款" />
										</td>
									</tr>
								</table>

							</form>
							<div style="clear: both;">
								&nbsp;
							</div>
						</div>
						<!-- end #content -->
						<div id="sidebar">

							<font size="2">已有中北微博帐号?</font>
							<form action="login.jsp" method="post">
								<input type="image" src="images/login.jpg">
								<hr size="1">
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
					<font size="2" color="#000000"> Copyright (c) 2008
						Sitename.com. All rights reserved. Design by <a href="#">zhongbei.weibo.com</a>.
					</font>
				</p>
			</div>
			<!-- end #footer -->
		</div>
	</body>
</html>

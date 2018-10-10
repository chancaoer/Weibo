package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.service.MsgInfoService;
import com.service.UserInfoService;
import com.service.UserService;
import com.bean.MsgInfo;
import com.bean.User;
import com.bean.UserInfo;

public class UserAction implements ServletResponseAware {

	private static final String User = null;

	private javax.servlet.http.HttpServletResponse response;

	@Resource
	UserService userService;
	@Resource
	MsgInfoService msgInfoService;
	@Resource
	UserInfoService userInfoService;

	User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	// login
	public String login() throws IOException {

		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		String reslut = "loginfaile";

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		user = userService.login(email, password);

		if (user != null) {
			reslut = "loginsuccess";

			session.setAttribute("user", user);

			session.setAttribute("loginuser_id", user.getId());

			int userid = user.getId();

			UserInfo userInfo = userInfoService.getUserInfoById(userid);

			session.setAttribute("userInfoCount", userInfo);

			session.setAttribute("userid", userid);

			List<MsgInfo> msgInfos = msgInfoService.getMsgAndFollows(userid);

			List<User> userList = new ArrayList<User>();

			for (MsgInfo msgInfo : msgInfos) {

				int user_id = msgInfo.getUser_id();

				User user = userService.getUserById(user_id);

				userList.add(user);

			}

			session.setAttribute("userList", userList);

			session.setAttribute("msgInfosLogin", msgInfos);

		} else {

			out.print("<script>alert('登录失败！')</script>");
			out.print("<script>window.location.href='login.jsp'</script>");

		}
		return reslut;
	}

	// 已收听
	public String getUsersFollows() throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		String str = "";

		int loginuser_id = (Integer) session.getAttribute("loginuser_id");

		System.out.println("loginuser_id====" + loginuser_id);

		List<User> users = userService.getUsersFollows(loginuser_id);

		session.setAttribute("usersFollows", users);

		str = "followsSuccess";

		return str;

	}

	// save(add)
	public String add() throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		User user = new User();

		String str = "addfaile";

		String email = request.getParameter("email");

		List<User> users = userService.getUsers();

		for (User us : users) {
			if (us.getEmail().equals(email)) {
				out.print("<script>alert('邮箱地址已经存在，请重新输入！')</script>");
				out
						.print("<script>window.location.href='register.jsp'</script>");

				str = "addfaile";
			}
		}

		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String pwd = request.getParameter("pwd");

		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");

		String birthday = year + "-" + month + "-" + day;

		String sex = request.getParameter("sex");
		String phone = request.getParameter("phone");
		String faceing = request.getParameter("faceing");

		if (email != "" && name != "" && password != "" && pwd != "") {

			user.setEmail(email);
			user.setName(name);
			user.setPassword(password);
			user.setBirthday(birthday);
			user.setSex(sex);
			user.setPhone(phone);
			user.setFaceing(faceing);

			userService.save(user);

			int userId = user.getId();

			System.out.println("userId" + userId);

			UserInfo userInfo = new UserInfo();

			userInfo.setUser_id(userId);
			userInfo.setMsg_count(0);
			userInfo.setFans_count(0);
			userInfo.setFollow_count(0);

			userInfoService.save(userInfo);

			str = "addsuccess";

		}

		return str;

	}

	// getUsers(所有)
	public String getUsers() throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String str = "";

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		int userid = (Integer) session.getAttribute("userid");

		System.out.println("userid-----------" + userid);

		UserInfo uInfo = userInfoService.getUserInfoById(userid);

		session.setAttribute("userInfoCount", null);
		session.setAttribute("userInfoCount", uInfo);

		List<User> users = userService.getUsersB(userid);

		session.setAttribute("usersAllB", users);

		str = "usersSuccess";

		return str;
	}
}
package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.service.UserInfoService;
import com.service.UserRelationService;
import com.bean.MsgInfo;
import com.bean.UserInfo;
import com.bean.UserRelation;

public class UserRelationAction implements ServletResponseAware {

	private static final String UserRelation = null;

	private javax.servlet.http.HttpServletResponse response;

	@Resource
	UserRelationService userRelationService;
	@Resource
	UserInfoService userInfoService;

	UserRelation userRelation;;

	public UserRelation getUserRelation() {
		return userRelation;
	}

	public void setUserRelation(UserRelation userRelation) {
		this.userRelation = userRelation;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	// save(add)
	public void add() throws IOException {

		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		String str = "";

		UserRelation userRelation = new UserRelation();

		int user_id = Integer.parseInt(request.getParameter("user_id"));

		int follow_id = Integer.parseInt(request.getParameter("follow_id"));

		userRelation.setUser_id(user_id);
		userRelation.setFollow_id(follow_id);
		userRelation.setW_type(0);

		userRelationService.save(userRelation);

		// 保存后，关注的对方fans_count加1
		UserInfo userInfo = userInfoService.getUserInfoById(follow_id);

		int fans_count = userInfo.getFans_count() + 1;
		userInfo.setFans_count(fans_count);

		userInfoService.updateFansCount(follow_id, fans_count);

		// 保存后，关注的我方follow_count加1
		userInfo = userInfoService.getUserInfoById(user_id);

		int follow_count = userInfo.getFollow_count() + 1;

		userInfo.setFollow_count(follow_count);

		userInfoService.updateFollowCount(user_id, follow_count);

		UserInfo uInfo = userInfoService.getUserInfoById(user_id);

		session.setAttribute("userInfoCount", null);
		session.setAttribute("userInfoCount", uInfo);

		str = "addSuccess";

	}

	// delete
	public void delete() throws IOException {
		System.out.println("ldjflsdjflsdjf");

		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		String str = "";

		int user_id = Integer.parseInt(request.getParameter("user_id"));

		int follow_id = Integer.parseInt(request.getParameter("follow_id"));

		List<UserRelation> userRelations = userRelationService
				.getUserRelationById(user_id, follow_id);

		for (UserRelation ur : userRelations) {
			if (userRelations.size() == 1) {

				int urid = ur.getId();

				userRelationService.deleteUserRelation(urid);

				// 删除后，关注的对方fans_count减1

				UserInfo userInfo = userInfoService.getUserInfoById(follow_id);

				int fans_count = userInfo.getFans_count() - 1;

				System.out.println("fans_count===" + fans_count);

				userInfo.setFans_count(fans_count);

				userInfoService.updateFansCount(follow_id, fans_count);

				// 删除后，关注的我方follow_count减1
				userInfo = userInfoService.getUserInfoById(user_id);

				int follow_count = userInfo.getFollow_count() - 1;

				System.out.println("follow_count===" + follow_count);

				userInfo.setFollow_count(follow_count);

				userInfoService.updateFollowCount(user_id, follow_count);

				UserInfo uInfo = userInfoService.getUserInfoById(user_id);

				session.setAttribute("userInfoCount", null);
				session.setAttribute("userInfoCount", uInfo);

				str = "deleteSuccess";
			} else {
				str = "deleteFaile";
			}

		}

	}
}
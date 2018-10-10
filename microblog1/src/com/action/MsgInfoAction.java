package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.service.MsgInfoService;
import com.service.MsgMsgRelationService;
import com.service.UserInfoService;
import com.service.UserMsgIndexService;
import com.service.UserService;
import com.bean.MsgInfo;
import com.bean.MsgMsgRelation;
import com.bean.User;
import com.bean.UserInfo;
import com.bean.UserMsgIndex;

public class MsgInfoAction implements ServletResponseAware {

	private static final String MsgInfo = null;

	private javax.servlet.http.HttpServletResponse response;

	@Resource
	MsgInfoService msgInfoService;
	@Resource
	UserInfoService userInfoService;
	@Resource
	UserService userService;
	@Resource
	UserMsgIndexService userMsgIndexService;
	@Resource
	MsgMsgRelationService msgMsgRelationService;

	MsgInfo msgInfo;

	public MsgInfo getMsgInfo() {
		return msgInfo;
	}

	public void setMsgInfo(MsgInfo msgInfo) {
		this.msgInfo = msgInfo;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	// save(add)
	public String add() throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		String str = "addfaile";

		MsgInfo msgInfo = new MsgInfo();

		int user_id = Integer.parseInt(request.getParameter("user_id"));

		System.out.println("user_id===" + user_id);

		String content = request.getParameter("content");

		if (content.trim() != "") {

			msgInfo.setUser_id(user_id);
			msgInfo.setContent(content);
			// 下面默认为0
			msgInfo.setW_type(0);
			msgInfo.setCommented_count(0);
			msgInfo.setComment_count(0);
			msgInfo.setTransferred_count(0);
			msgInfo.setTransfer_count(0);
			Date r_time = new Date();

			msgInfo.setR_time(r_time);

			msgInfoService.save(msgInfo);

			UserMsgIndex userMsgIndex = new UserMsgIndex();

			userMsgIndex.setUser_id(user_id);
			userMsgIndex.setAuthor_id(user_id);
			userMsgIndex.setMsg_id(msgInfo.getId());
			userMsgIndex.setR_time(r_time);

			userMsgIndexService.save(userMsgIndex);

			UserInfo userInfo = userInfoService.getUserInfoById(msgInfo
					.getUser_id());

			int msg_count = userInfo.getMsg_count() + 1;

			userInfoService.updateMsgCount(userInfo.getUser_id(), msg_count);

			UserInfo uInfo = userInfoService.getUserInfoById(msgInfo
					.getUser_id());

			session.setAttribute("userInfoCount", null);
			session.setAttribute("userInfoCount", uInfo);

			// update msg list in index.jsp
			List<User> userList = new ArrayList<User>();
			List<MsgInfo> msgInfos = msgInfoService.getMsgAndFollows(user_id);

			for (MsgInfo msgInfo_obj : msgInfos) {

				int user_ids = msgInfo_obj.getUser_id();

				User user = userService.getUserById(user_ids);

				userList.add(user);

			}
			session.removeAttribute("userList");
			session.removeAttribute("msgInfosLogin");
			session.setAttribute("userList", userList);
			session.setAttribute("msgInfosLogin", msgInfos);
			str = "addsuccess";
		}

		return str;

	}

	// save(转播)
	public String saveZhuanBo() throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		String str = "";

		MsgInfo msgInfo = new MsgInfo();

		int user_id = Integer.parseInt(request.getParameter("zhuanboid"));

		int author_id = Integer.parseInt(request.getParameter("author_id"));

		int yuanmsgid = Integer.parseInt(request.getParameter("yuanmsgid"));

		System.out.println("author_id===" + author_id);

		String content = request.getParameter("msg_content");

		if (content.trim() != "") {

			msgInfo.setUser_id(user_id);
			msgInfo.setContent(content);

			msgInfo.setW_type(2);// 0原创，1评论，2表示转播
			// 下面默认为0
			msgInfo.setCommented_count(0);
			msgInfo.setComment_count(0);
			msgInfo.setTransferred_count(0);
			msgInfo.setTransfer_count(0);
			Date r_time = new Date();

			msgInfo.setR_time(r_time);

			msgInfoService.save(msgInfo);

			UserMsgIndex userMsgIndex = new UserMsgIndex();

			userMsgIndex.setUser_id(user_id);
			userMsgIndex.setAuthor_id(author_id);
			userMsgIndex.setMsg_id(msgInfo.getId());
			userMsgIndex.setR_time(r_time);

			userMsgIndexService.save(userMsgIndex);

			MsgMsgRelation msgMsgRelation = new MsgMsgRelation();

			msgMsgRelation.setReferce_id(user_id);
			msgMsgRelation.setReferce_msg_id(msgInfo.getId());

			msgMsgRelation.setReferced_id(author_id);
			msgMsgRelation.setReferenced_msg_id(yuanmsgid);
			msgMsgRelation.setR_time(r_time);
			msgMsgRelation.setW_type(2);

			msgMsgRelationService.save(msgMsgRelation);

			UserInfo userInfo = userInfoService.getUserInfoById(user_id);

			int msg_count = userInfo.getMsg_count() + 1;

			userInfoService.updateMsgCount(user_id, msg_count);

			UserInfo uInfo = userInfoService.getUserInfoById(user_id);

			session.setAttribute("userInfoCount", null);
			session.setAttribute("userInfoCount", uInfo);

			List<User> userList = new ArrayList<User>();
			List<MsgInfo> msgInfos = msgInfoService.getMsgAndFollows(user_id);

			for (MsgInfo msgInfo_obj : msgInfos) {

				int user_ids = msgInfo_obj.getUser_id();

				User user = userService.getUserById(user_ids);

				userList.add(user);

			}
			session.removeAttribute("userList");
			session.removeAttribute("msgInfosLogin");
			session.setAttribute("userList", userList);
			session.setAttribute("msgInfosLogin", msgInfos);

			str = "saveSuccess";
		}
		return str;

	}

	// save(评论)
	public String savePingLun() throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		String str = "";

		MsgInfo msgInfo = new MsgInfo();

		int user_id = Integer.parseInt(request.getParameter("pinglunid"));

		int authorping_id = Integer.parseInt(request
				.getParameter("authorping_id"));

		int yuanmsgpingid = Integer.parseInt(request
				.getParameter("yuanmsgpingid"));

		System.out.println("authorping_id===" + authorping_id);

		String content = request.getParameter("msgping_content");

		if (content.trim() != "") {

			msgInfo.setUser_id(user_id);
			msgInfo.setContent(content);

			msgInfo.setW_type(1);// 0原创，1评论，2表示转播
			// 下面默认为0
			msgInfo.setCommented_count(0);
			msgInfo.setComment_count(0);
			msgInfo.setTransferred_count(0);
			msgInfo.setTransfer_count(0);
			Date r_time = new Date();

			msgInfo.setR_time(r_time);

			msgInfoService.save(msgInfo);

			UserMsgIndex userMsgIndex = new UserMsgIndex();

			userMsgIndex.setUser_id(user_id);
			userMsgIndex.setAuthor_id(authorping_id);
			userMsgIndex.setMsg_id(msgInfo.getId());
			userMsgIndex.setR_time(r_time);

			userMsgIndexService.save(userMsgIndex);

			MsgMsgRelation msgMsgRelation = new MsgMsgRelation();

			msgMsgRelation.setReferce_id(user_id);
			msgMsgRelation.setReferce_msg_id(msgInfo.getId());

			msgMsgRelation.setReferced_id(authorping_id);
			msgMsgRelation.setReferenced_msg_id(yuanmsgpingid);
			msgMsgRelation.setR_time(r_time);
			msgMsgRelation.setW_type(1);

			msgMsgRelationService.save(msgMsgRelation);

			List<User> userList = new ArrayList<User>();
			List<MsgInfo> msgInfos = msgInfoService.getMsgAndFollows(user_id);

			for (MsgInfo msgInfo_obj : msgInfos) {

				int user_ids = msgInfo_obj.getUser_id();

				User user = userService.getUserById(user_ids);

				userList.add(user);

			}
			session.removeAttribute("userList");
			session.removeAttribute("msgInfosLogin");
			session.setAttribute("userList", userList);
			session.setAttribute("msgInfosLogin", msgInfos);

			str = "savepingSuccess";
		}
		return str;

	}

	// getMsgInfos
	public String getMsgInfos() throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String str = "";

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		List<MsgInfo> msgInfos = msgInfoService.getMsgInfos();

		session.setAttribute("msgInfosAll", msgInfos);

		str = "msgInfosSuccess";

		return str;

	}

	// getMsgInfosTop
	public String getMsgInfosTop() throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		String str = "";

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		List<MsgInfo> msgInfos = msgInfoService.getMsgInfosTop();
		msgInfos = msgInfos.subList(0, 3);
		List<User> usersList = new ArrayList<User>();

		for (MsgInfo msgInfo : msgInfos) {

			int user_id = msgInfo.getUser_id();

			User user = userService.getUserById(user_id);

			usersList.add(user);

		}
		System.out.println("test--->");

		session.setAttribute("usersMsgList", usersList);

		session.setAttribute("msgInfosTop", msgInfos);
		str = "msgInfosTopSuccess";
		System.out.print(str);

		return str;

	}

	// 删除
	public String deleteMsgInfo() throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		int msgInfoId = Integer.parseInt(request.getParameter("id"));

		System.out.println("msgInfoId" + msgInfoId);

		MsgInfo msgInfo = msgInfoService.getMsgInfoById(msgInfoId);

		msgInfoService.deleteMsgInfo(msgInfoId);

		List<User> userList = new ArrayList<User>();

		List<MsgInfo> msgInfos = msgInfoService.getMsgAndFollows(msgInfo
				.getUser_id());

		for (MsgInfo msgInfo_obj : msgInfos) {

			int user_ids = msgInfo_obj.getUser_id();

			User user = userService.getUserById(user_ids);

			userList.add(user);

		}
		session.removeAttribute("userList");
		session.removeAttribute("msgInfosLogin");

		session.setAttribute("userList", userList);
		session.setAttribute("msgInfosLogin", msgInfos);

		UserInfo userInfo = userInfoService.getUserInfoById(msgInfo
				.getUser_id());

		int msg_count = userInfo.getMsg_count() - 1;

		System.out.println("msg_count==" + msg_count);

		userInfoService.updateMsgCount(userInfo.getUser_id(), msg_count);

		UserInfo uInfo = userInfoService.getUserInfoById(msgInfo.getUser_id());

		session.setAttribute("userInfoCount", null);
		session.setAttribute("userInfoCount", uInfo);

		String str = "deleteScuccess";

		return str;
	}
}
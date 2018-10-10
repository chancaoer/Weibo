package com.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.service.UserInfoService;
import com.bean.UserInfo;

public class UserInfoAction implements ServletResponseAware {

	private static final String UserInfo = null;

	private javax.servlet.http.HttpServletResponse response;

	@Resource
	UserInfoService userInfoService;

	UserInfo userInfo;

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

}
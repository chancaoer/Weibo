package com.service;

import java.util.List;

import javax.annotation.Resource;

import com.bean.UserInfo;
import com.bean.UserRelation;

public class UserInfoService {
	@Resource
	Service service;

	public void save(UserInfo userInfo) {
		service.save(userInfo);
	}

	// 根据Id查用户
	public UserInfo getUserInfoById(int user_id) {
		String hql = "from UserInfo where user_id=?";
		UserInfo userInfo = service.getUserInfoById(hql, user_id);

		return userInfo;

	}


	// 修改广播数量
	public void updateMsgCount(int userid, int msg_count) {
		String hql = "from UserInfo where user_id=?";
		UserInfo userInfo = service.getUserInfoById(hql, userid);

		userInfo.setMsg_count(msg_count);

		service.update(userInfo);

	}

	// 修改粉丝数量
	public void updateFansCount(int userid, int fans_count) {
		String hql = "from UserInfo where user_id=?";
		UserInfo userInfo = service.getUserInfoById(hql, userid);

		userInfo.setFans_count(fans_count);

		service.update(userInfo);

	}

	// 修改关注数量
	public void updateFollowCount(int userid, int follow_count) {
		String hql = "from UserInfo where user_id=?";
		UserInfo userInfo = service.getUserInfoById(hql, userid);

		userInfo.setFollow_count(follow_count);

		service.update(userInfo);

	}
}
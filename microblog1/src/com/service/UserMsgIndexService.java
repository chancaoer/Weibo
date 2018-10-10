package com.service;

import java.util.List;

import javax.annotation.Resource;

import com.bean.UserMsgIndex;

public class UserMsgIndexService {
	@Resource
	Service service;

	public void save(UserMsgIndex userMsgIndex) {
		service.save(userMsgIndex);
	}

	// 根据Id查集合
	@SuppressWarnings("unchecked")
	public List<UserMsgIndex> getUserMsgIndexsById(int user_id) {

		String hql = "from UserMsgIndex where user_id=?";

		List<UserMsgIndex> userMsgIndexs = service.getInfosById(hql, user_id);

		return userMsgIndexs;

	}

	// 删除
	public void deleteUserMsgIndex(int id) {
		service.deleteUserMsgIndex(id);
	}

}
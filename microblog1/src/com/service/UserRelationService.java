package com.service;

import java.util.List;

import javax.annotation.Resource;

import com.bean.UserRelation;

public class UserRelationService {
	@Resource
	Service service;

	public void save(UserRelation userRelation) {
		service.save(userRelation);
	}

	// 根据Id查用户
	@SuppressWarnings("unchecked")
	public List<UserRelation> getUserInfosById(int user_id) {
		String hql = "from UserRelation where user_id=? and w_type=0";

		List<UserRelation> userRelations = service.getInfosById(hql, user_id);

		return userRelations;

	}

	// 删除
	public void deleteUserRelation(int id) {
		service.deleteUserRelation(id);
	}

	// 根据Id查用户
	public List<UserRelation> getUserRelationById(int user_id, int follow_id) {
		String hql = "from UserRelation where user_id=? and follow_id=?";
		List<UserRelation> userRelations = service.getUserRelationById(hql,
				user_id, follow_id);
		return userRelations;
	}
}
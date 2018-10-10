package com.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.bean.MsgInfo;
import com.bean.User;
import com.bean.UserRelation;

public class UserService {
	@Resource
	Service service;

	// 登录
	public User login(String email, String password) {
		String hql = "from User where email=? and password=?";
		User user = service.login(hql, email, password);
		return user;
	}

	public void save(User user) {
		service.save(user);
	}

	public List<User> getUsers() {
		List<Object> obj = service.getAll("from User");
		List<User> users = new ArrayList<User>();

		for (Object user : obj) {
			User us = (User) user;
			users.add(us);
		}
		return users;

	}

	// 用户（除了自己及自己关注的人）
	@SuppressWarnings("unchecked")
	public List<User> getUsersB(int id) {
		// 1.首先查找t_user_relation表，根据传进来的id查找，返回的是集合
		// from UserRelation o where o.user_id=?
		// 返回的是我关注的人的集合List xxx:假如有3个(li,wa,zh)
		// 2.再去 t_user表中查找我没有关注的人
		// from User o where o.id not in(li,wa,zh,id)
		// 返回的是我没有关注的人的集合
		String hqls = "from UserRelation ur where ur.user_id=?";

		List<UserRelation> userRelations = service.getInfosById(hqls, id);

		String hql = "from User u where u.id not in(" + id;// ?,?,?)
		// from User u where u.id not in(id,

		if (userRelations != null) {
			StringBuilder sb = new StringBuilder(hql);
			for (UserRelation ur : userRelations) {
				sb.append(",");
				sb.append(ur.getFollow_id());
			}
			// from User u where u.id not in(xx,aa,
			sb.append(")");
			hql = sb.toString();
		} else {
			StringBuilder sb = new StringBuilder(hql);
			sb.append(")");
			hql = sb.toString();
		}

		List<User> users = service.getInfosById(hql);

		return users;
	}

	// 用户（除了自己及自己关注的人）
	@SuppressWarnings("unchecked")
	public List<User> getUsersFollows(int id) {
		String hqls = "from UserRelation where user_id=?";

		List<UserRelation> userRelations = service.getInfosById(hqls, id);

		String hql = "from User where id in(";

		if (userRelations != null) {
			StringBuilder sb = new StringBuilder(hql);
			for (UserRelation ur : userRelations) {
				sb.append(ur.getFollow_id());
				sb.append(",");
			}
			// from User where id in(xx,aa,
			sb.delete(sb.length() - 1, sb.length());
			sb.append(")");
			hql = sb.toString();
		} else {
			StringBuilder sb = new StringBuilder(hql);
			sb.append("null)");
			hql = sb.toString();
		}

		List<User> users = service.getInfosById(hql);

		return users;
	}

	// 根据id查找用户
	public User getUserById(int id) {
		String hql = "from User where id=?";
		User user = service.getUserById(hql, id);

		return user;

	}

}
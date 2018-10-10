package com.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bean.MsgInfo;
import com.bean.MsgMsgRelation;
import com.bean.User;
import com.bean.UserInfo;
import com.bean.UserRelation;

@Transactional
public interface Service {
	// 用户登录
	public User login(String hql, String email, String password);

	// 保存（添加）
	public abstract void save(Object obj);

	// 修改
	public abstract void update(Object obj);

	// 删除
	public abstract void deleteUser(int userId);

	// 删除
	public abstract void deleteMsgInfo(int msgInfoId);

	// 删除
	public abstract void deleteUserMsgIndex(int userMsgIndexId);

	// 删除
	public abstract void deleteUserRelation(int id);

	// 所有
	public abstract List<Object> getAll(String hql);

	// 显示所有除了自己
	public abstract List<Object> getInfosB(String hql, int id);

	// 根据id查找User
	public abstract User getUserById(String hql, int id);

	// 根据id查找MsgInfo
	public abstract MsgInfo getMsgInfoById(String hql, int id);

	// 根据id查找UserInfo
	public abstract UserInfo getUserInfoById(String hql, int id);

	// 根据id查找User
	public abstract MsgMsgRelation getMsgMsgRelationById(String hql, int id);

	// 根据user_id and follow_id查找UserRelation
	public abstract List<UserRelation> getUserRelationById(String hql, int userId,
			int followId);

	// 根据id查到集合
	public abstract List getInfosById(String hql, int id);

	// 查到集合
	public abstract List getInfosById(String hql);

	// 利用一个条件查另一个
	public abstract List<Object> getInfos(String hql, int number1, int number2);

}
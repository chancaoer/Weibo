package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bean.MsgInfo;
import com.bean.MsgMsgRelation;
import com.bean.User;
import com.bean.UserInfo;
import com.bean.UserMsgIndex;
import com.bean.UserRelation;
import com.service.Service;

@Transactional
public class ServiceBean implements Service {
	@Resource
	public SessionFactory sessionFactory;

	public User login(String hql, String email, String password) { 
		return (User) sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, email).setParameter(1, password)
				.uniqueResult();
	}

	public void save(Object obj) {
		sessionFactory.getCurrentSession().persist(obj);
	}

	@SuppressWarnings("unchecked")
	public List<Object> getAll(String hql) {
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	public User getUserById(String hql, int id) {
		return (User) sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, id).uniqueResult();
	}

	public MsgInfo getMsgInfoById(String hql, int id) {
		return (MsgInfo) sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, id).uniqueResult();
	}

	public UserInfo getUserInfoById(String hql, int id) {
		return (UserInfo) sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, id).uniqueResult();
	}

	public MsgMsgRelation getMsgMsgRelationById(String hql, int id) {
		return (MsgMsgRelation) sessionFactory.getCurrentSession().createQuery(
				hql).setParameter(0, id).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<UserRelation> getUserRelationById(String hql, int userId,
			int followId) {
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, userId).setParameter(1, followId).list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List getInfosById(String hql, int id) {
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, id).list();
	}

	public void update(Object obj) {
		sessionFactory.getCurrentSession().merge(obj);

	}

	public void deleteMsgInfo(int msgInfoId) {
		sessionFactory.getCurrentSession().delete(
				sessionFactory.getCurrentSession().load(MsgInfo.class,
						msgInfoId));

	}

	public void deleteUser(int userId) {
		sessionFactory.getCurrentSession().delete(
				sessionFactory.getCurrentSession().load(User.class, userId));

	}

	public void deleteUserMsgIndex(int userMsgIndexId) {
		sessionFactory.getCurrentSession().delete(
				sessionFactory.getCurrentSession().load(UserMsgIndex.class,
						userMsgIndexId));
	}

	public void deleteUserRelation(int id) {
		sessionFactory.getCurrentSession()
				.delete(
						sessionFactory.getCurrentSession().load(
								UserRelation.class, id));
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Object> getInfos(String hql, int number1, int number2) {
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, number1).setParameter(1, number2).list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Object> getInfosB(String hql, int id) {
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, id).list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List getInfosById(String hql) {
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

}

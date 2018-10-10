package com.service;

import javax.annotation.Resource;

import com.bean.MsgMsgRelation;

public class MsgMsgRelationService {
	@Resource
	Service service;

	public void save(MsgMsgRelation msgMsgRelation) {
		service.save(msgMsgRelation);
	}

	// 根据Id查用户
	public MsgMsgRelation getMsgMsgRelationById(int id) {
		String hql = "from MsgMsgRelation where referce_msg_id=?";
		MsgMsgRelation msgMsgRelation = service.getMsgMsgRelationById(hql, id);

		return msgMsgRelation;

	}
}
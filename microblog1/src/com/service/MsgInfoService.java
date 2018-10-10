package com.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.html.dom.HTMLBuilder;
import org.w3c.dom.html.HTMLDivElement;

import com.bean.MsgInfo;
import com.bean.MsgMsgRelation;
import com.bean.User;
import com.bean.UserRelation;
import com.opensymphony.oscache.web.tag.UseCachedTag;

public class MsgInfoService {
	@Resource
	Service service;

	// 添加
	public void save(MsgInfo msgInfo) {
		service.save(msgInfo);
	}

	// 删除
	public void deleteMsgInfo(int id) {
		service.deleteMsgInfo(id);
	}

	// 全部
	public List<MsgInfo> getMsgInfos() {
		List<Object> obj = service.getAll("from MsgInfo order by r_time desc");
		List<MsgInfo> msgInfos = new ArrayList<MsgInfo>();

		for (Object msgInfo : obj) {
			MsgInfo msg = (MsgInfo) msgInfo;
			msgInfos.add(msg);
		}
		return msgInfos;
	}

	// 自己及我关注的人的广播
	@SuppressWarnings("unchecked")
	public List<MsgInfo> getMsgAndFollows(int user_id) {

		String hql = "from UserRelation where user_id=?";

		List<UserRelation> userRelations = service.getInfosById(hql, user_id);

		String hqls = "from MsgInfo where user_id in(" + user_id;

		if (userRelations != null) {
			StringBuilder sb = new StringBuilder(hqls);
			for (UserRelation ur : userRelations) {
				sb.append(",");
				sb.append(ur.getFollow_id());
			}
			sb.append(")  order by r_time desc");
			hqls = sb.toString();
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append(")  order by r_time desc");
			hqls = sb.toString();
		}

		List<MsgInfo> msgInfos = service.getInfosById(hqls);
		int i = -1;
		for (MsgInfo msg : msgInfos) {
			i++;
			int w_type = msg.getW_type();
			if (w_type == 2) {
				int msgInfo_id = msg.getId();
				String hql1 = "from MsgMsgRelation where referce_msg_id=?";
				MsgMsgRelation msgMsgRelation = service.getMsgMsgRelationById(
						hql1, msgInfo_id);
				int referenced_msg_id = msgMsgRelation.getReferenced_msg_id();

				String hql2 = "from MsgInfo where id=?";
				MsgInfo msgInfo = service.getMsgInfoById(hql2,
						referenced_msg_id);
				StringBuffer str = new StringBuffer();
				str.append("转播：");
				str.append(msgInfos.get(i).getContent());
				str.append("#!@");
				int u_id = msgInfo.getUser_id();

				User user = service.getUserById("from User where id=?", u_id);

				String u_name = user.getName();

				str.append(u_name + "：");

				str.append(msgInfo.getContent());

				str.append("#!@");

				str.append(msgInfo.getR_time());

				msgInfos.get(i).setContent(str.toString());

			}
			if (w_type == 1) {
				int msgInfo_id = msg.getId();
				String hql1 = "from MsgMsgRelation where referce_msg_id=?";
				MsgMsgRelation msgMsgRelation = service.getMsgMsgRelationById(
						hql1, msgInfo_id);
				int referenced_msg_id = msgMsgRelation.getReferenced_msg_id();

				String hql2 = "from MsgInfo where id=?";
				MsgInfo msgInfo = service.getMsgInfoById(hql2,
						referenced_msg_id);
				StringBuffer str = new StringBuffer();
				str.append("评论：");
				str.append(msgInfos.get(i).getContent());
				str.append("#!@");
				int u_id = msgInfo.getUser_id();

				User user = service.getUserById("from User where id=?", u_id);

				String u_name = user.getName();

				str.append(u_name + "：");

				str.append(msgInfo.getContent());

				str.append("#!@");

				str.append(msgInfo.getR_time());

				msgInfos.get(i).setContent(str.toString());

			}
		}

		return msgInfos;

	}

	// top 10
	@SuppressWarnings("unchecked")
	public List getMsgInfosTop() {
		List<MsgInfo> msgInfos = service
				.getInfosById("from MsgInfo order by r_time desc");

		return msgInfos;
	}

	// 根据id查找msgInfo(一个)
	public MsgInfo getMsgInfoById(int id) {
		String hql = "from MsgInfo where id=?";
		MsgInfo msgInfo = service.getMsgInfoById(hql, id);

		return msgInfo;
	}

	// 根据id查msgInfo(多个)
	@SuppressWarnings("unchecked")
	public List<MsgInfo> getMsgInfosById(int id) {
		String hql = "from MsgInfo where user_id=? ";
		List<Object> obj = service.getInfosById(hql, id);
		List<MsgInfo> msgInfos = new ArrayList<MsgInfo>();

		for (Object msgInfo : obj) {
			MsgInfo msg = (MsgInfo) msgInfo;
			msgInfos.add(msg);
		}
		return msgInfos;
	}
}
package com.bean;

import java.util.Date;

public class UserMsgIndex {
	private Integer id;// 主键
	private Integer user_id;// 用户编号
	private Integer author_id;// 消息发布者编号（被关注者或自己）
	private Integer msg_id;// 消息编号
	private Date r_time;// 发布时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(Integer author_id) {
		this.author_id = author_id;
	}

	public Integer getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(Integer msg_id) {
		this.msg_id = msg_id;
	}

	public Date getR_time() {
		return r_time;
	}

	public void setR_time(Date r_time) {
		this.r_time = r_time;
	}
}
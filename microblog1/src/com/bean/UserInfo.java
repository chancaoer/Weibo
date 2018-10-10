package com.bean;

public class UserInfo {
	private Integer id;// 主键、自动增长
	private Integer user_id;// 外键、关联t_user表中
	private Integer msg_count;// 发布消息的总数
	private Integer fans_count;// 粉丝总数
	private Integer follow_count;// 关注的总人数

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer userId) {
		user_id = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMsg_count() {
		return msg_count;
	}

	public void setMsg_count(Integer msg_count) {
		this.msg_count = msg_count;
	}

	public Integer getFans_count() {
		return fans_count;
	}

	public void setFans_count(Integer fans_count) {
		this.fans_count = fans_count;
	}

	public Integer getFollow_count() {
		return follow_count;
	}

	public void setFollow_count(Integer follow_count) {
		this.follow_count = follow_count;
	}

}
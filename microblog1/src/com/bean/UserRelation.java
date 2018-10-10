package com.bean;

public class UserRelation {
	private Integer id;// 主键
	private Integer user_id;// 用户编号
	private Integer follow_id;// 被关注者的id
	private Integer w_type;// 关注的类型(0:粉丝,1:被关注者)

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

	public Integer getFollow_id() {
		return follow_id;
	}

	public void setFollow_id(Integer follow_id) {
		this.follow_id = follow_id;
	}

	public Integer getW_type() {
		return w_type;
	}

	public void setW_type(Integer wType) {
		w_type = wType;
	}

}
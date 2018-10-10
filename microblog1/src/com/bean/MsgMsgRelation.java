package com.bean;

import java.util.Date;

public class MsgMsgRelation {
	private Integer id;// 主键
	private Integer referce_id;// 引用者的编号
	private Integer referce_msg_id;// 引用消息编号
	private Integer referced_id;// 消息的原始作者的编号
	private Integer referenced_msg_id;// 原始消息的编号
	private Integer w_type;// 操作类型(1评论 2转发)
	private Date r_time;// 发布时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getReferce_id() {
		return referce_id;
	}

	public void setReferce_id(Integer referce_id) {
		this.referce_id = referce_id;
	}

	public Integer getReferce_msg_id() {
		return referce_msg_id;
	}

	public void setReferce_msg_id(Integer referce_msg_id) {
		this.referce_msg_id = referce_msg_id;
	}

	public Integer getReferced_id() {
		return referced_id;
	}

	public void setReferced_id(Integer referced_id) {
		this.referced_id = referced_id;
	}

	public Integer getReferenced_msg_id() {
		return referenced_msg_id;
	}

	public void setReferenced_msg_id(Integer referenced_msg_id) {
		this.referenced_msg_id = referenced_msg_id;
	}

	public Integer getW_type() {
		return w_type;
	}

	public void setW_type(Integer w_type) {
		this.w_type = w_type;
	}

	public Date getR_time() {
		return r_time;
	}

	public void setR_time(Date r_time) {
		this.r_time = r_time;
	}

}
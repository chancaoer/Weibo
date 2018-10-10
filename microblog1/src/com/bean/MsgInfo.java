package com.bean;

import java.util.Date;

public class MsgInfo {
	private Integer id;// 主键，消息编号
	private Integer user_id;// 用户编号
	private String content;// 消息内容
	private Integer w_type;// 消息类型（0:原创,1:评论 2转发）
	private Integer commented_count;// 评论过的数量（只增不减）
	private Integer comment_count;// 保留的评论数量
	private Integer transferred_count;// 转发数量（只增不减
	private Integer transfer_count;// 保留转发数量
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getW_type() {
		return w_type;
	}

	public void setW_type(Integer w_type) {
		this.w_type = w_type;
	}

	public Integer getCommented_count() {
		return commented_count;
	}

	public void setCommented_count(Integer commented_count) {
		this.commented_count = commented_count;
	}

	public Integer getComment_count() {
		return comment_count;
	}

	public void setComment_count(Integer comment_count) {
		this.comment_count = comment_count;
	}

	public Integer getTransferred_count() {
		return transferred_count;
	}

	public void setTransferred_count(Integer transferred_count) {
		this.transferred_count = transferred_count;
	}

	public Integer getTransfer_count() {
		return transfer_count;
	}

	public void setTransfer_count(Integer transfer_count) {
		this.transfer_count = transfer_count;
	}

	public Date getR_time() {
		return r_time;
	}

	public void setR_time(Date r_time) {
		this.r_time = r_time;
	}

}
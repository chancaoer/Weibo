package com.bean;

public class User {
	private Integer id;// 主键、自动增长
	private String name;// 姓名
	private String password;// 密码
	private String sex;// 性别
	private String birthday;// 生日
	private String email;// 邮箱
	private String phone;// 电话
	private String faceing;// 头像

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFaceing() {
		return faceing;
	}

	public void setFaceing(String faceing) {
		this.faceing = faceing;
	}
}
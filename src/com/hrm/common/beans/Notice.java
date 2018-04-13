package com.hrm.common.beans;

import java.util.Date;

public class Notice {
	private Integer id;
	private String title;
	private String content;
	private Date createDate;
	private Integer user_id;
	private User user;
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", content=" + content + ", createDate=" + createDate
				+ ", user_id=" + user_id + ", user=" + user + "]";
	}
	
}

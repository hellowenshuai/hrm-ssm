package com.hrm.common.beans;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Document {
	private Integer id;
	private String title;
	private MultipartFile file;
	private String filename;
	private String remark;
	private Date create_date;
	private Integer user_id;
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
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
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "Document [id=" + id + ", title=" + title + ", file=" + file + ", filename=" + filename + ", remark="
				+ remark + ", create_date=" + create_date + ", user_id=" + user_id + ", user=" + user + "]";
	}
	
	
	

}

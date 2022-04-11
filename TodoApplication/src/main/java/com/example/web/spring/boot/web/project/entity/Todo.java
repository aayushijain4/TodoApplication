package com.example.web.spring.boot.web.project.entity;

import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class Todo {
	private int id;
    private String user;
    
    @Size(min=5,message="Enter atleast 5 characters")
    private String desc;
    private Date targetDate;
    private boolean isDone;
	public Todo() {
		super();
	}
	
	public Todo(int id, String user, String desc, Date targetDate, boolean isDone) {
		super();
		this.id = id;
		this.user = user;
		this.desc = desc;
		this.targetDate = targetDate;
		this.isDone = isDone;
	}
	public int getId() {
		return id;
	}
	public String getUser() {
		return user;
	}
	public String getDesc() {
		return desc;
	}
	public Date getTargetDate() {
		return targetDate;
	}
	public boolean isDone() {
		return isDone;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	@Override
	public String toString() {
		return "Todo [id=" + id + ", user=" + user + ", desc=" + desc + ", targetDate=" + targetDate + ", isDone="
				+ isDone + "]";
	}
    
    
}

package com.oraclejava.domain.model;

import java.io.Serializable;
import java.util.Date;

public class SendMailing implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int num;
	private int jobId;
	private String fromEmailAddr;
	private String title;
	private String body;
	private Date reservedDate;
	private Date deliveriedDate;
	private int sendType;
	private String contentType;
	private Date regdate;
	private String toEmailAddr;
	private String uid;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getFromEmailAddr() {
		return fromEmailAddr;
	}
	public void setFromEmailAddr(String fromEmailAddr) {
		this.fromEmailAddr = fromEmailAddr;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Date getReservedDate() {
		return reservedDate;
	}
	public void setReservedDate(Date reservedDate) {
		this.reservedDate = reservedDate;
	}
	public Date getDeliveriedDate() {
		return deliveriedDate;
	}
	public void setDeliveriedDate(Date deliveriedDate) {
		this.deliveriedDate = deliveriedDate;
	}
	public int getSendType() {
		return sendType;
	}
	public void setSendType(int sendType) {
		this.sendType = sendType;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getToEmailAddr() {
		return toEmailAddr;
	}
	public void setToEmailAddr(String toEmailAddr) {
		this.toEmailAddr = toEmailAddr;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	
}

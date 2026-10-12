package com.oraclejava.domain.model;

import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable {

	private static final long serialVersionUID = 1L;
	private String uid;
	private String pwd;
	private String uname;
	private String email;
	private String jiktel;
	private String handphone;
	private String address;
	private String companyname;
	private String donggi;
	private String bigo;
	private Date regdate;
	private Boolean outYn;
	private Boolean isAdmin;
	private Boolean isStaff;
	private Boolean isTeacher;
	private String authority;
	private Integer authorityLevel;
	private String empid;
	private String store;
	private String chkCom;
	private String jointype;
	private String joinkeyword;
	private Boolean mailRejection;
	private String division;
	private Integer divSeq;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJiktel() {
		return jiktel;
	}
	public void setJiktel(String jiktel) {
		this.jiktel = jiktel;
	}
	public String getHandphone() {
		return handphone;
	}
	public void setHandphone(String handphone) {
		this.handphone = handphone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getDonggi() {
		return donggi;
	}
	public void setDonggi(String donggi) {
		this.donggi = donggi;
	}
	public String getBigo() {
		return bigo;
	}
	public void setBigo(String bigo) {
		this.bigo = bigo;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Boolean getOutYn() {
		return outYn;
	}
	public void setOutYn(Boolean outYn) {
		this.outYn = outYn;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Boolean getIsStaff() {
		return isStaff;
	}
	public void setIsStaff(Boolean isStaff) {
		this.isStaff = isStaff;
	}
	public Boolean getIsTeacher() {
		return isTeacher;
	}
	public void setIsTeacher(Boolean isTeacher) {
		this.isTeacher = isTeacher;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Integer getAuthorityLevel() {
		return authorityLevel;
	}
	public void setAuthorityLevel(Integer authorityLevel) {
		this.authorityLevel = authorityLevel;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getChkCom() {
		return chkCom;
	}
	public void setChkCom(String chkCom) {
		this.chkCom = chkCom;
	}
	public String getJointype() {
		return jointype;
	}
	public void setJointype(String jointype) {
		this.jointype = jointype;
	}
	public String getJoinkeyword() {
		return joinkeyword;
	}
	public void setJoinkeyword(String joinkeyword) {
		this.joinkeyword = joinkeyword;
	}
	public Boolean getMailRejection() {
		return mailRejection;
	}
	public void setMailRejection(Boolean mailRejection) {
		this.mailRejection = mailRejection;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public Integer getDivSeq() {
		return divSeq;
	}
	public void setDivSeq(Integer divSeq) {
		this.divSeq = divSeq;
	}
	
	
	
	
}

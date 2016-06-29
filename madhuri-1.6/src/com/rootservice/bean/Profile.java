package com.rootservice.bean;

public class Profile {

	private String fullname;
	private String emailid;
	private String userid;
	
	public Profile(String fullname,String emailid,String userid){
		this.fullname = fullname;
		this.emailid = emailid;
		this.userid = userid;
	}
	
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
}

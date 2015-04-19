package org.tfa.dto;

import java.util.Date;

public class SilanisCallbackDTO {
	private String name;
	private String sessionUser;
	private String packageId;
	private Date createdDate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSessionUser() {
		return sessionUser;
	}
	public void setSessionUser(String sessionUser) {
		this.sessionUser = sessionUser;
	}
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append(String.format("       name: %s\n", name));
		str.append(String.format("sessionUser: %s\n", sessionUser));
		str.append(String.format("  packageId: %s\n", packageId));
		return str.toString();
	}
}

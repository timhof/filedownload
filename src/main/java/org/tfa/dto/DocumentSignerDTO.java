package org.tfa.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DocumentSignerDTO {

	String name;
	String id;
	Date signedDate;
	boolean completed;
	SignerDTO signer;
	List<String[]> fieldValues;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getSignedDate() {
		return signedDate;
	}
	public void setSignedDate(Date signedDate) {
		this.signedDate = signedDate;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public List<String[]> getFieldValues() {
		return fieldValues;
	}
	public void setFieldValues(List<String[]> fieldValues) {
		this.fieldValues = fieldValues;
	}
	
	public void addField(String[] field){
		if(this.fieldValues == null){
			this.fieldValues = new ArrayList<String[]>();
		}
		this.fieldValues.add(field);
	}
	public SignerDTO getSigner() {
		return signer;
	}
	public void setSigner(SignerDTO signer) {
		this.signer = signer;
	}
}

package org.tfa.dto;

import java.util.List;

import com.silanis.esl.api.model.AttachmentRequirement;

public class SignerDTO {

	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String type;
	List<AttachmentRequirement> attachmentRequirements;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<AttachmentRequirement> getAttachmentRequirements() {
		return attachmentRequirements;
	}
	public void setAttachmentRequirements(
			List<AttachmentRequirement> attachmentRequirements) {
		this.attachmentRequirements = attachmentRequirements;
	}
}

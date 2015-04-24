package org.tfa.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PackageSummaryDTO {

	private String name;
	private String status;
	private Date updated;
	private List<DocumentSummaryDTO> documents;
	
	public List<DocumentSummaryDTO> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentSummaryDTO> documents) {
		this.documents = documents;
	}
	
	public void addDocumentSummary(DocumentSummaryDTO documentSummaryDTO){
		if(this.documents == null){
			this.documents = new ArrayList<DocumentSummaryDTO>();
		}
		this.documents.add(documentSummaryDTO);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

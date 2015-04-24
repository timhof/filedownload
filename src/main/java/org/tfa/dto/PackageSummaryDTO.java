package org.tfa.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PackageSummaryDTO {

	private List<DocumentSummaryDTO> documents;
	private String status;
	private Date updated;
	private String name;
	
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

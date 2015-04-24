package org.tfa.dto;

import java.util.ArrayList;
import java.util.List;

public class PackageSummaryDTO {

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
}

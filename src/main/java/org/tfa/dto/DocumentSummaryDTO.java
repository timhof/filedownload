package org.tfa.dto;

import java.util.ArrayList;
import java.util.List;

public class DocumentSummaryDTO {

	private String name;
	private String id;
	private List<DocumentSignerDTO> documentSigners;
	
	public List<DocumentSignerDTO> getDocumentSigners() {
		return documentSigners;
	}

	public void setDocumentSigners(List<DocumentSignerDTO> documentSigners) {
		this.documentSigners = documentSigners;
	}
	
	public void addDocumentSigner(DocumentSignerDTO documentSignerDTO){
		if(this.documentSigners == null){
			this.documentSigners = new ArrayList<DocumentSignerDTO>();
		}
		this.documentSigners.add(documentSignerDTO);
	}

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
}

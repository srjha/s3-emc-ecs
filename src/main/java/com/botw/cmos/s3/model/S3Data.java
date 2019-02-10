package com.botw.cmos.s3.model;

import com.amazonaws.services.s3.model.S3ObjectSummary;

public class S3Data {
	private String documentId;
	private S3ObjectSummary summary;

	public S3Data() {
		super();
	}

	public S3Data(String documentId, S3ObjectSummary summary) {
		super();
		this.documentId = documentId;
		this.summary = summary;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public S3ObjectSummary getSummary() {
		return summary;
	}

	public void setSummary(S3ObjectSummary summary) {
		this.summary = summary;
	}

}

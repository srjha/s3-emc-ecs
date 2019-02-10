package com.botw.cmos.s3.controller;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.botw.cmos.s3.model.S3Data;
import com.botw.cmos.s3.service.S3DataService;

@RestController("/s3/api")
public class S3DataController {

	@Autowired
	private S3DataService dataService;

	@GetMapping("/metadata")
	public List<S3Data> getAll() {
		return dataService.getAllMetadata();
	}

	@GetMapping("/content/{DOC_ID}")
	public ResponseEntity<byte[]> get(@PathVariable("DOC_ID") String docId) {

		ByteArrayOutputStream downloadFile = dataService.downloadFile(docId);

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + docId + "\"")
				.body(downloadFile.toByteArray());

	}

}

package com.botw.cmos.s3.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.botw.cmos.s3.model.S3Data;

@Service
public class S3DataService {

	private static final Logger LOGGER = LoggerFactory.getLogger(S3DataService.class);

	@Value("${bucketName}")
	private String bucketName;

	@Autowired
	private AmazonS3 s3Client;

	public List<S3Data> getAllMetadata() {
		List<S3Data> dataToReturn = new ArrayList<>();
		s3Client.listObjects(bucketName).getObjectSummaries().forEach(s -> dataToReturn.add(new S3Data(s.getKey(), s)));
		return dataToReturn;
	}

	public ByteArrayOutputStream downloadFile(String key) {
		try {
			S3Object s3Object = s3Client.getObject(bucketName, key);
			S3ObjectInputStream objectContentInputStream = s3Object.getObjectContent();

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] byteContentBufer = new byte[4096];
			int length;
			while ((length = objectContentInputStream.read(byteContentBufer, 0, byteContentBufer.length)) != -1) {
				outputStream.write(byteContentBufer, 0, length);
			}
			return outputStream;
		} catch (IOException e) {
			String msg = "Error while trying to download file from s3 bucket " + bucketName + " for key " + key;
			LOGGER.error(msg, e);
			throw new ApplicationException(msg, e);
		}

	}
}

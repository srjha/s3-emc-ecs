package com.botw.cmos.s3.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3ClientConfig {

	@Value("${awsAccessKeyId}")
	private String awsAccessKeyId;

	@Value("${awsSecretAccessKey}")
	private String awsSecretAccessKey;

	@Value("${awsRegion}")
	private String awsRegion;

	@Bean
	public AmazonS3 amazonS3Client() {

		AWSCredentials credentials = new BasicAWSCredentials(awsAccessKeyId, awsSecretAccessKey);
		AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);
		AmazonS3 awsS3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(awsRegion))
				.withCredentials(credentialsProvider).build();
		return awsS3Client;

	}

}

package com.carrentingservice.vehiclelisting.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class CommonConfiguration {

	@Value("${s3.bucket.url}")
	private String s3BucketURL;

	@Value("${s3.bucket.name}")
	private String s3BucketName;

	@Value("${aws.access.key}")
	private String awsAccessKey;

	@Value("${aws.secret.access.key}")
	private String secretAccessKey;

	@Value("${aws.region}")
	private String awsRegion;

}

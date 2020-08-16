package com.carrentingservice.vehiclelisting.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.carrentingservice.vehiclelisting.configuration.CommonConfiguration;
import com.carrentingservice.vehiclelisting.service.AWSService;

@Service
public class AWSServiceImpl implements AWSService {

	private AmazonS3 s3Client;

	@Autowired
	private CommonConfiguration commonConfiguration;

	@PostConstruct
	private AmazonS3 amazonS3Client() {
		if (null == s3Client) {
			AWSCredentials awsCredentials = new BasicAWSCredentials(commonConfiguration.getAwsAccessKey(),
					commonConfiguration.getSecretAccessKey());
			s3Client = AmazonS3ClientBuilder.standard()
					.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
					.withRegion(Regions.valueOf(commonConfiguration.getAwsRegion())).build();
		}
		return s3Client;
	}

	@Override
	public List<String> uploadFilesToS3(List<MultipartFile> carImages) throws IOException {
		List<String> carImagesURL = new ArrayList<>();
		for (MultipartFile multipartFile : carImages) {
			String imageURL = "";
			File file = convertMultipartFileToFile(multipartFile);
			String fileName = generateFileName(multipartFile);
			s3Client.putObject(new PutObjectRequest(commonConfiguration.getS3BucketName(), fileName, file)
					.withCannedAcl(CannedAccessControlList.PublicRead));
			imageURL = commonConfiguration.getS3BucketURL() + fileName;
			file.delete();
			carImagesURL.add(imageURL);
		}
		return carImagesURL;
	}

	private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
		File file = new File(multipartFile.getOriginalFilename());
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		fileOutputStream.write(multipartFile.getBytes());
		fileOutputStream.close();
		return file;
	}

	private String generateFileName(MultipartFile multipartFile) {
		return new Date().getTime() + "-" + multipartFile.getOriginalFilename().replace(" ", "_");
	}
}

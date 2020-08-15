package com.carrentingservice.vehiclelisting.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
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
import com.amazonaws.services.s3.model.PutObjectResult;
import com.carrentingservice.vehiclelisting.configuration.CommonConfiguration;
import com.carrentingservice.vehiclelisting.service.AWSService;

@Service
public class AWSServiceImpl implements AWSService {

	final static Logger LOGGER = Logger.getLogger(AWSServiceImpl.class);

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
		LOGGER.info("**************11111111111111");
		LOGGER.info("------------------2222222222222222");
		for (MultipartFile multipartFile : carImages) {
			LOGGER.info("------------------333333333333333333333");
			String imageURL = "";
			LOGGER.info("------------------444444444444444444444");
			File file = convertMultipartFileToFile(multipartFile);
			LOGGER.info("------------------555555555555555555");
			LOGGER.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + file.getName());
			String fileName = generateFileName(multipartFile);
			LOGGER.info("()()()()()()()()() " + fileName);
			LOGGER.info("------------------66666666666666666666666666");
			LOGGER.info("------------------" + commonConfiguration.getS3BucketName());
			PutObjectResult por = s3Client
					.putObject(new PutObjectRequest(commonConfiguration.getS3BucketName(), fileName, file)
							.withCannedAcl(CannedAccessControlList.PublicRead));
			LOGGER.info(por);
			LOGGER.info("------------------7777777777777777777777777");
			imageURL = commonConfiguration.getS3BucketURL() + fileName;
			LOGGER.info("------------------88888888888888888888888888888888888");
			file.delete();
			LOGGER.info("------------------99999999999999999999999999999999");
			carImagesURL.add(imageURL);
			LOGGER.info("------------------10101010101010101001001");
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

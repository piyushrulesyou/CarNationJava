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
		System.out.println("**************11111111111111");
		try {
			System.out.println("------------------2222222222222222");
			for (MultipartFile multipartFile : carImages) {
				System.out.println("------------------333333333333333333333");
				String imageURL = "";
				System.out.println("------------------444444444444444444444");
				File file = convertMultipartFileToFile(multipartFile);
				System.out.println("------------------555555555555555555");
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+file.getName());
				String fileName = generateFileName(multipartFile);
				System.out.println("()()()()()()()()() " + fileName);
				System.out.println("------------------66666666666666666666666666");
				System.out.println("------------------" + commonConfiguration.getS3BucketName());
				s3Client.putObject(new PutObjectRequest(commonConfiguration.getS3BucketName(), fileName, file)
						.withCannedAcl(CannedAccessControlList.PublicRead));
				System.out.println("------------------7777777777777777777777777");
				imageURL = commonConfiguration.getS3BucketURL() + fileName;
				System.out.println("------------------88888888888888888888888888888888888");
				file.delete();
				System.out.println("------------------99999999999999999999999999999999");
				carImagesURL.add(imageURL);
				System.out.println("------------------10101010101010101001001");
			}
		} catch (IOException e) {
			e.printStackTrace();
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

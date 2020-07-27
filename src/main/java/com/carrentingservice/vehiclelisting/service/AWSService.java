package com.carrentingservice.vehiclelisting.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AWSService {

	public List<String> uploadFilesToS3(List<MultipartFile> carImages) throws IOException;
}

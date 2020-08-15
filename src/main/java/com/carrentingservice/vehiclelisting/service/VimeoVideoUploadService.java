package com.carrentingservice.vehiclelisting.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface VimeoVideoUploadService {

	public String uploadVideoToVimeoServer(List<MultipartFile> file, String username, String taskName)
			throws IOException;

}
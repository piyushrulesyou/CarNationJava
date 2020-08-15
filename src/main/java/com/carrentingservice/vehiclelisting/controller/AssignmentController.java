package com.carrentingservice.vehiclelisting.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.carrentingservice.vehiclelisting.controller.dto.ResponseTO;
import com.carrentingservice.vehiclelisting.service.VimeoVideoUploadService;

@CrossOrigin
@RestController
@RequestMapping("/assignment")
public class AssignmentController {


	@Autowired
	private VimeoVideoUploadService vimeoVideoUploadService;


	@PostMapping("/upload-assignment-video")
	public ResponseEntity<ResponseTO<String>> uploadVimeoVideo(
			@RequestParam("assignmentVideo") MultipartFile assignmentVideo, @RequestParam("username") String username,
			@RequestParam("taskName") String taskName) throws IOException {
		ResponseTO<String> responseTO = new ResponseTO<>();
		List<MultipartFile> list = new ArrayList<>();
		list.add(assignmentVideo);
		list.add(assignmentVideo);
		String videoURL = vimeoVideoUploadService.uploadVideoToVimeoServer(list, username, taskName);
		responseTO.setData(videoURL);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}
}

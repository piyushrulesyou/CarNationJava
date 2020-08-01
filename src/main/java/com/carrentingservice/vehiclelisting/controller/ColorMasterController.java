package com.carrentingservice.vehiclelisting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrentingservice.vehiclelisting.controller.dto.ColorMasterDTO;
import com.carrentingservice.vehiclelisting.controller.dto.ResponseTO;
import com.carrentingservice.vehiclelisting.delegate.ColorMasterDelegate;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;

@RestController
@RequestMapping("/colors")
public class ColorMasterController {

	@Autowired
	private ColorMasterDelegate colorMasterDelegate;

	@GetMapping(value = "/get-colors")
	public ResponseEntity<ResponseTO<List<ColorMasterDTO>>> getAllColors() throws RecordNotFoundException {
		ResponseTO<List<ColorMasterDTO>> responseTO = new ResponseTO<>();
		List<ColorMasterDTO> colorMasterResponseTO = colorMasterDelegate.getAllColors();
		responseTO.setData(colorMasterResponseTO);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}
}

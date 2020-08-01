package com.carrentingservice.vehiclelisting.delegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carrentingservice.vehiclelisting.controller.dto.ColorMasterDTO;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;
import com.carrentingservice.vehiclelisting.service.ColorMasterService;

@Component
public class ColorMasterDelegate {

	@Autowired
	private ColorMasterService colorMasterService;

	public List<ColorMasterDTO> getAllColors() throws RecordNotFoundException {
		return colorMasterService.findAllColors();
	}
}
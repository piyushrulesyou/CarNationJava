package com.carrentingservice.vehiclelisting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrentingservice.vehiclelisting.controller.dto.CityMasterResponseDTO;
import com.carrentingservice.vehiclelisting.controller.dto.ResponseTO;
import com.carrentingservice.vehiclelisting.delegate.CityMasterDelegate;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;

@RestController
@RequestMapping("/cities")
public class CityMasterController {

	@Autowired
	private CityMasterDelegate cityMasterDelegate;

	@GetMapping(value = "/get-cities")
	public ResponseEntity<ResponseTO<CityMasterResponseDTO>> getAllCities() throws RecordNotFoundException {
		ResponseTO<CityMasterResponseDTO> responseTO = new ResponseTO<>();
		CityMasterResponseDTO cityMasterResponseTO = cityMasterDelegate.getAllCities();
		responseTO.setData(cityMasterResponseTO);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}

}

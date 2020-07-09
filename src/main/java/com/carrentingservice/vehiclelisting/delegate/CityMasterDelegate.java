package com.carrentingservice.vehiclelisting.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carrentingservice.vehiclelisting.controller.dto.CityMasterResponseDTO;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;
import com.carrentingservice.vehiclelisting.service.CityMasterService;

@Component
public class CityMasterDelegate {

	@Autowired
	private CityMasterService cityMasterService;

	public CityMasterResponseDTO getAllCities() throws RecordNotFoundException {
		return cityMasterService.findAllCities();
	}

	public CityMasterResponseDTO getActiveCities() throws RecordNotFoundException {
		return cityMasterService.findActiveCities();
	}

}

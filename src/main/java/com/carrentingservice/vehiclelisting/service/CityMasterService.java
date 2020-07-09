package com.carrentingservice.vehiclelisting.service;

import org.springframework.stereotype.Service;

import com.carrentingservice.vehiclelisting.controller.dto.CityMasterResponseDTO;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;

@Service
public interface CityMasterService {

	public CityMasterResponseDTO findAllCities() throws RecordNotFoundException;

	public CityMasterResponseDTO findActiveCities() throws RecordNotFoundException;

}

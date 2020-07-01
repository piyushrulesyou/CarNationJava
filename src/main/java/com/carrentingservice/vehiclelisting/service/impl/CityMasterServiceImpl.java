package com.carrentingservice.vehiclelisting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrentingservice.vehiclelisting.constants.ErrorConstants;
import com.carrentingservice.vehiclelisting.controller.dto.CityMasterResponseDTO;
import com.carrentingservice.vehiclelisting.domain.CityMasterEntity;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;
import com.carrentingservice.vehiclelisting.repo.VehicleCityMasterRepo;
import com.carrentingservice.vehiclelisting.service.CityMasterService;
import com.carrentingservice.vehiclelisting.service.mappers.CityMasterMapper;

@Service
public class CityMasterServiceImpl implements CityMasterService {

	@Autowired
	private VehicleCityMasterRepo vehicleCityMasterRepo;

	@Autowired
	private CityMasterMapper cityMasterMapper;

	@Override
	public CityMasterResponseDTO findAllCities() throws RecordNotFoundException {
		List<CityMasterEntity> cityMasterEntity = vehicleCityMasterRepo.findAll();
		if (cityMasterEntity.isEmpty())
			throw new RecordNotFoundException("Error occured in method " + " findAllCities() " + " of class "
					+ this.getClass().getName() + ". Exception code is " + ErrorConstants.CITY_NOT_FOUND_ERROR_CODE
					+ " and exception message is " + ErrorConstants.CITY_NOT_FOUND_ERROR + ".");
		CityMasterResponseDTO cityMasterDTO = new CityMasterResponseDTO();
		cityMasterDTO.setCities(cityMasterMapper.toCityMasterDTO(cityMasterEntity));
		return cityMasterDTO;
	}
}

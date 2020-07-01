package com.carrentingservice.vehiclelisting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrentingservice.vehiclelisting.constants.ErrorConstants;
import com.carrentingservice.vehiclelisting.controller.dto.VehicleProducerResponseDTO;
import com.carrentingservice.vehiclelisting.domain.ProducerTypeEntity;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;
import com.carrentingservice.vehiclelisting.repo.VehicleProducerRepo;
import com.carrentingservice.vehiclelisting.service.VehicleProducerService;
import com.carrentingservice.vehiclelisting.service.mappers.VehicleProducerMapper;

@Service
public class VehicleProducerServiceImpl implements VehicleProducerService {

	@Autowired
	private VehicleProducerMapper vehicleProducerMapper;

	@Autowired
	private VehicleProducerRepo vehicleProducerRepo;

	@Override
	public VehicleProducerResponseDTO getAllVehicleProducers() throws RecordNotFoundException {
		List<ProducerTypeEntity> producersList = vehicleProducerRepo.findAll();
		if (producersList.isEmpty())
			throw new RecordNotFoundException("Error occured in method " + " getAllVehicleProducers() " + " of class "
					+ this.getClass().getName() + ". Exception code is " + ErrorConstants.PRODUCER_NOT_FOUND_ERROR_CODE
					+ " and exception message is " + ErrorConstants.PRODUCER_NOT_FOUND_ERROR + ".");
		VehicleProducerResponseDTO vehicleProducerDTO = new VehicleProducerResponseDTO();
		vehicleProducerDTO.setProducers(vehicleProducerMapper.toProducerTypeDTO(producersList));
		return vehicleProducerDTO;
	}
}

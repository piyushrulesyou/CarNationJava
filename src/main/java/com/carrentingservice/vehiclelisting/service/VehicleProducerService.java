package com.carrentingservice.vehiclelisting.service;

import org.springframework.stereotype.Service;

import com.carrentingservice.vehiclelisting.controller.dto.VehicleProducerResponseDTO;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;

@Service
public interface VehicleProducerService {

	public VehicleProducerResponseDTO getAllVehicleProducers() throws RecordNotFoundException;

}

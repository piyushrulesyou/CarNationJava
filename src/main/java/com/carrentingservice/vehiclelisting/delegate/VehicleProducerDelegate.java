package com.carrentingservice.vehiclelisting.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carrentingservice.vehiclelisting.controller.dto.VehicleProducerResponseDTO;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;
import com.carrentingservice.vehiclelisting.service.VehicleProducerService;

@Component
public class VehicleProducerDelegate {

	@Autowired
	private VehicleProducerService vehicleProducerService;

	public VehicleProducerResponseDTO getAllVehicleProducers() throws RecordNotFoundException {
		return vehicleProducerService.getAllVehicleProducers();
	}
}

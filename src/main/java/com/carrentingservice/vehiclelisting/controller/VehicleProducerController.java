package com.carrentingservice.vehiclelisting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrentingservice.vehiclelisting.controller.dto.ResponseTO;
import com.carrentingservice.vehiclelisting.controller.dto.VehicleProducerResponseDTO;
import com.carrentingservice.vehiclelisting.delegate.VehicleProducerDelegate;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;

@RestController
@RequestMapping("/producer")
public class VehicleProducerController {

	@Autowired
	private VehicleProducerDelegate vehicleProducerDelegate;

	@GetMapping(value = "/get-producers")
	public ResponseEntity<ResponseTO<VehicleProducerResponseDTO>> getAllVehicleProducers()
			throws RecordNotFoundException {
		ResponseTO<VehicleProducerResponseDTO> responseTO = new ResponseTO<>();
		VehicleProducerResponseDTO vehicleProducerResponseTO = vehicleProducerDelegate.getAllVehicleProducers();
		responseTO.setData(vehicleProducerResponseTO);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}
}

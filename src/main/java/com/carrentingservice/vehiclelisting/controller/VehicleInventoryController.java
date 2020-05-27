package com.carrentingservice.vehiclelisting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrentingservice.vehiclelisting.controller.dto.ResponseTO;
import com.carrentingservice.vehiclelisting.delegate.VehicleInventoryDelegate;
import com.carrentingservice.vehiclelisting.domain.VehicleInventoryEntity;

@RestController
public class VehicleInventoryController {

	@Autowired
	private VehicleInventoryDelegate vehicleInventoryDelegate;

	@GetMapping(value = "/vehicle-inventory")
	public ResponseEntity<ResponseTO<List<VehicleInventoryEntity>>> getVehicleInventory() {
		ResponseTO<List<VehicleInventoryEntity>> responseTO = new ResponseTO<>();
		List<VehicleInventoryEntity> vehicleInventory = vehicleInventoryDelegate.getVehicleInventory();
		responseTO.setData(vehicleInventory);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}
	
//	@GetMapping(value = "/vehicle-city")
//	public ResponseEntity<ResponseTO<List<VehicleCityManyToManyEntity>>> getVehicleCity() {
//		ResponseTO<List<VehicleCityManyToManyEntity>> responseTO = new ResponseTO<>();
//		List<VehicleCityManyToManyEntity> vehicleInventory = vehicleInventoryDelegate.getVehicleCity();
//		responseTO.setData(vehicleInventory);
//		return new ResponseEntity<>(responseTO, HttpStatus.OK);
//	}
}

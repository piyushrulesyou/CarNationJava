package com.carrentingservice.vehiclelisting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carrentingservice.vehiclelisting.controller.dto.InventoryRequestDTO;
import com.carrentingservice.vehiclelisting.controller.dto.ResponseTO;
import com.carrentingservice.vehiclelisting.controller.dto.VehicleInventoryDTO;
import com.carrentingservice.vehiclelisting.delegate.VehicleInventoryDelegate;

@RestController
public class VehicleInventoryController {

	@Autowired
	private VehicleInventoryDelegate vehicleInventoryDelegate;

	@GetMapping(value = "/vehicle-inventory")
	public ResponseEntity<ResponseTO<List<VehicleInventoryDTO>>> getVehicleInventory() throws Exception {
		ResponseTO<List<VehicleInventoryDTO>> responseTO = new ResponseTO<>();
		List<VehicleInventoryDTO> vehicleInventory = vehicleInventoryDelegate.getVehicleInventory();
		responseTO.setData(vehicleInventory);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}

	@PostMapping(value = "/add-inventory")
	public ResponseEntity<ResponseTO<InventoryRequestDTO>> addInventory(
			@RequestBody InventoryRequestDTO inventoryDetails) {
		ResponseTO<InventoryRequestDTO> responseTO = new ResponseTO<>();
		InventoryRequestDTO addInventory = vehicleInventoryDelegate.addInventory(inventoryDetails);
		responseTO.setData(addInventory);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}

}
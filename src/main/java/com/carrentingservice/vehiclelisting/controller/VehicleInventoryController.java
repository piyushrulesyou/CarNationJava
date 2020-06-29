package com.carrentingservice.vehiclelisting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrentingservice.vehiclelisting.controller.dto.InventoryRequestDTO;
import com.carrentingservice.vehiclelisting.controller.dto.ResponseTO;
import com.carrentingservice.vehiclelisting.controller.dto.VehicleInventoryDTO;
import com.carrentingservice.vehiclelisting.delegate.VehicleInventoryDelegate;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;

@RestController
@RequestMapping("/vehicle-inventory")
public class VehicleInventoryController {

	@Autowired
	private VehicleInventoryDelegate vehicleInventoryDelegate;

	@GetMapping(value = "/get-vehicle")
	public ResponseEntity<ResponseTO<List<VehicleInventoryDTO>>> getVehicleInventory() throws RecordNotFoundException {
		ResponseTO<List<VehicleInventoryDTO>> responseTO = new ResponseTO<>();
		List<VehicleInventoryDTO> vehicleInventory = vehicleInventoryDelegate.getVehicleInventory();
		responseTO.setData(vehicleInventory);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}

	@GetMapping(value = "/get-vehicle/{vehicleId}")
	public ResponseEntity<ResponseTO<VehicleInventoryDTO>> getVehicleInventoryById(
			@PathVariable("vehicleId") String vehicleId) throws RecordNotFoundException {
		ResponseTO<VehicleInventoryDTO> responseTO = new ResponseTO<>();
		VehicleInventoryDTO vehicleInventory = vehicleInventoryDelegate.getVehicleInventoryById(vehicleId);
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
